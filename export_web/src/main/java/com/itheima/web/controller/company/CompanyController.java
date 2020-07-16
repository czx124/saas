package com.itheima.web.controller.company;

import com.itheima.domain.company.Company;
import com.itheima.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/list")
    public ModelAndView findAll(){
//        int i = 1/0;      //模拟异常
        ModelAndView mv = new ModelAndView();
        List<Company> list = companyService.findAll();
        mv.setViewName("company/company-list");
        mv.addObject("list", list);
        return mv;
    }

    @RequestMapping(value = "/save",name = "测试方法")
    public String save(Date date){
        System.out.println(date);
        return "success";
    }


    @RequestMapping(value = "/home")
    public String home(){
        return "home/home";
    }

    @RequestMapping(value = "/toAdd")
    public String toAdd(){
        return "company/company-add";
    }

    @RequestMapping(value = "/toUpdate")
    public ModelAndView toUpdate(String id){
        ModelAndView mv = new ModelAndView();
        Company company = companyService.findById(id);
        mv.setViewName("company/company-update");
        mv.addObject("company", company);
        return mv;
    }

    @RequestMapping(value = "/edit")
    public String edit(Company company){
        if(StringUtils.isEmpty(company.getId())){
            companyService.save(company);
        }else {
            companyService.update(company);
        }
        return "redirect:/company/list";
    }

    @RequestMapping(value = "/delete")
    public String edit(String id){
        companyService.delete(id);
        return "redirect:/company/list";
    }

}
