package com.itheima.dao.system.dept;

import com.itheima.domain.system.dept.Dept;

import java.util.List;

/**
 * @author Jackson
 */

public interface DeptDao {

    List<Dept> findAll(String companyId);

    Dept findById(String id);

    void save(Dept dept);

    void update(Dept dept);

    void delete(String id);

    List<Dept> findByParentId(String id);
}
