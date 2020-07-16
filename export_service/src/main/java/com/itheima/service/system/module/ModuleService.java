package com.itheima.service.system.module;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.module.Module;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/13 13:40
 */
public interface ModuleService {
    //根据id查询
    Module findById(String id);

    //查询全部
    PageInfo<Module> findByPage(int pageNum, int pageSize);

    //根据id删除
    void delete(String id);

    //添加
    void save(Module module);

    //更新
    void update(Module module);

    //查询全部
    List<Module> findAll();

    List<Module> findModuleByRoleId(String roleId);

    List<Module> findModulesByUid(String userId);

}
