package com.itheima.service.company.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.company.CompanyDao;
import com.itheima.domain.company.Company;
import com.itheima.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;
import java.util.UUID;
@Service(timeout = 100000)
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Override
    public List<Company> findAll() {

        return companyDao.findAll();
    }

    @Override
    public void save(Company company) {
        company.setId(UUID.randomUUID().toString());
        companyDao.save(company);
    }

    @Override
    public void update(Company company) {
        companyDao.update(company);
    }

    @Override
    public Company findById(String id) {
        return companyDao.findById(id);
    }

    @Override
    public void delete(String id) {
        companyDao.delete(id);
    }
}
