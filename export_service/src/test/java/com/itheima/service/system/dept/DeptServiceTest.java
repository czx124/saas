package com.itheima.service.system.dept;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.dept.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class DeptServiceTest {
    @Autowired
    private DeptService deptService;
    @Test
    public void findAllTest(){
        PageInfo<Dept> list = deptService.findAll("1", 1, 3);
        System.out.println(list);
    }

    @Test
    public void delete(){
        boolean delete = deptService.delete("100");
        System.out.println(delete);
    }



}
