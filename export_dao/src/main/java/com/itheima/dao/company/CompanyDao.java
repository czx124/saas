package com.itheima.dao.company;

import com.itheima.domain.company.Company;

import java.util.List;

public interface CompanyDao {

    List<Company> findAll();

    void save(Company company);

    void update(Company company);

    Company findById(String id);

    void delete(String id);

}
