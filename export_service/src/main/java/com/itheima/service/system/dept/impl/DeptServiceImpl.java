package com.itheima.service.system.dept.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.dept.DeptDao;
import com.itheima.domain.system.dept.Dept;
import com.itheima.service.system.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Jackson
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public PageInfo<Dept> findAll(String companyId, Integer pageNum, Integer PageSize) {
        PageHelper.startPage(pageNum, PageSize);
        List<Dept> list = deptDao.findAll(companyId);
        return new PageInfo<>(list);
    }

    @Override
    public List<Dept> findDeptList(String companyId) {
        return deptDao.findAll(companyId);
    }

    @Override
    public void save(Dept dept) {
        dept.setId(UUID.randomUUID().toString());
        deptDao.save(dept);
    }

    @Override
    public void update(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    @Override
    public boolean delete(String id) {
        List<Dept> list = deptDao.findByParentId(id);
        if(list == null || list.size() == 0){
            deptDao.delete(id);
            return true;
        }else {
            return false;
        }
    }
}
