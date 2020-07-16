package com.itheima.dao.system.dept;

import com.itheima.domain.system.dept.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/12 14:36
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")


public class DeptDaoTest {
    @Autowired
    private DeptDao deptDao;

    @Test
    public void findAll() {
        List<Dept> all = deptDao.findAll("1");
        all.forEach(System.out::println);
    }


    @Test
    public void findById() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByParentId() {
        List<Dept> list = deptDao.findByParentId("100");
        System.out.println(list.size());
    }
}