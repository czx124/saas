package com.itheima.service.system.role;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.role.Role;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/13 13:05
 */
public interface RoleService {

    PageInfo<Role> findByPage(String companyId, int pageNum, int PageSize);

    List<Role> findAll(String companyId);

    void save(Role role);

    void update(Role role);

    boolean delete(String id);

    Role findById(String id);

    void updateRoleModule(String roleId, String moduleIds);

    List<Role> findByUid(String id);

}
