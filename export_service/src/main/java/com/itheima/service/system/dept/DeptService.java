package com.itheima.service.system.dept;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.dept.Dept;

import java.util.List;

/**
 * @author Jackson
 */
public interface DeptService {

    PageInfo<Dept> findAll(String companyId, Integer pageNum, Integer PageSize);

    List<Dept> findDeptList(String companyId);

    void save(Dept dept);

    void update(Dept dept);

    Dept findById(String id);

    boolean delete(String id);
}
