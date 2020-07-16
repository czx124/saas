package com.itheima.dao.system.module;

import com.itheima.domain.system.module.Module;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/13 13:37
 */
public interface ModuleDao {
    //根据id查询
    Module findById(String moduleId);

    //根据id删除
    void delete(String moduleId);

    //添加
    void save(Module module);

    //更新
    void update(Module module);

    //查询全部
    List<Module> findAll();

    List<Module> findModuleByRoleId(String roleId);


    List<Module> findModuleByBelong(String belong);

    List<Module> findModuleByUid(String userId);
}
