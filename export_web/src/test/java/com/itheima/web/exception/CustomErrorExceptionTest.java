package com.itheima.web.exception;

import com.itheima.domain.company.Company;
import com.itheima.service.company.CompanyService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class CustomErrorExceptionTest {
    @Autowired
    private CompanyService companyService;
    @Test
    public void test(){
        List<Company> all = companyService.findAll();
    }

    @Test
    public void test1(){

        String username = "user1_1@export.com";
        String password = "1";
        System.out.println(new Md5Hash(password,username).toString());
    }
}
