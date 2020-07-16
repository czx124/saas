package com.itheima.dao.system.role;

import com.itheima.domain.system.role.Role;
import com.itheima.domain.system.roleModule.RoleModule;
import com.itheima.domain.system.roleUser.RoleUser;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/13 12:59
 */

public interface RoleDao {
    //根据id查询
    Role findById(String id);

    //查询全部
    List<Role> findAll(String companyId);

    //根据id删除
    void delete(String id);

    //添加
    void save(Role role);

    //更新
    void update(Role role);

    void deleteRoleModuleByRoleId(String roleId);

    void saveRoleModule(String roleId, String moduleId);

    List<Role> findByUid(String id);

    List<RoleUser> findRoleUserById(String id);

    List<RoleModule> findRoleModuleById(String id);
}
