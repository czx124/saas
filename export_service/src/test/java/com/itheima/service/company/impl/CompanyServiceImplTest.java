package com.itheima.service.company.impl;

import com.itheima.domain.company.Company;
import com.itheima.service.company.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class CompanyServiceImplTest {
    @Autowired
    private CompanyService companyService;
    @Test
    public void demoTest(){
        List<Company> all = companyService.findAll();
        System.out.println(all);
    }
}
