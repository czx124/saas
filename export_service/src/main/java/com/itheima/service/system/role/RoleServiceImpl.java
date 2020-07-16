package com.itheima.service.system.role;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.role.RoleDao;
import com.itheima.dao.system.user.UserDao;
import com.itheima.domain.system.role.Role;
import com.itheima.domain.system.roleModule.RoleModule;
import com.itheima.domain.system.roleUser.RoleUser;
import com.itheima.domain.system.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Jackson
 * @date 2020/7/13 13:12
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public PageInfo<Role> findByPage(String companyId, int pageNum, int PageSize) {
        //1.调用startPage方法
        PageHelper.startPage(pageNum,PageSize);
        //2.查询全部列表
        List<Role> list = roleDao.findAll(companyId);
        //构造pagebean
        return new PageInfo<Role>(list);
    }

    @Override
    public List<Role> findAll(String companyId) {
        return roleDao.findAll(companyId);
    }

    @Override
    public void save(Role role) {
        role.setId(UUID.randomUUID().toString());
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public boolean delete(String id) {
        List<RoleUser> roleUserList = roleDao.findRoleUserById(id);
        List<RoleModule> roleModuleList = roleDao.findRoleModuleById(id);
        if(roleModuleList!=null && roleModuleList.size()>0){
            return false;
        }else if (roleUserList!=null && roleUserList.size()>0){
            return false;
        }else {
            roleDao.delete(id);
            return true;
        }


    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        //角色分配权限
        //1. 删除角色的权限
        //DELETE FROM pe_role_module WHERE role_id=?
        roleDao.deleteRoleModuleByRoleId(roleId);

        //2. 角色添加权限
        //INSERT INTO pe_role_module(role_id,module_id)VALUES(?,?)
        if (!StringUtils.isEmpty(moduleIds)) {
            String[] array = moduleIds.split(",");
            if (array != null && array.length>0){
                for (String moduleId : array) {
                    roleDao.saveRoleModule(roleId,moduleId);
                }
            }
        }
    }

    @Override
    public List<Role> findByUid(String id) {
        return roleDao.findByUid(id);
    }

}
