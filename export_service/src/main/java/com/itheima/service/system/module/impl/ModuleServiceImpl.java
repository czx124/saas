package com.itheima.service.system.module.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.module.ModuleDao;
import com.itheima.dao.system.user.UserDao;
import com.itheima.domain.system.module.Module;
import com.itheima.domain.system.user.User;
import com.itheima.service.system.module.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Jackson
 * @date 2020/7/13 13:41
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Module findById(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public PageInfo<Module> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Module> list = moduleDao.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public void delete(String id) {
        moduleDao.delete(id);

    }

    @Override
    public void save(Module module) {
        module.setId(UUID.randomUUID().toString());
        moduleDao.save(module);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    @Override
    public List<Module> findModuleByRoleId(String roleId) {
        return moduleDao.findModuleByRoleId(roleId);
    }

    @Override
    public List<Module> findModulesByUid(String userId) {
        User user = userDao.findById(userId);
        if(user.getDegree()==0){
            return moduleDao.findModuleByBelong("0");
        }else if (user.getDegree()==1) {
            return moduleDao.findModuleByBelong("1");
        }else {
            return moduleDao.findModuleByUid(userId);
        }


    }
}
