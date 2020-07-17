package com.itheima.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.domain.company.Company;
import com.itheima.service.company.CompanyService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jackson
 * @date 2020/7/17 19:14
 */
@RestController
public class ApplyController {

    @Reference
    private CompanyService companyService;
    @RequestMapping("/apply")
    public String apply(Company company){
        try {
            companyService.save(company);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return "0";
        }
    }
}
