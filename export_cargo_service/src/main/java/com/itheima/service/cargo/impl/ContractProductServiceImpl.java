package com.itheima.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.ContractProduct;
import com.itheima.domain.cargo.ContractProductExample;
import com.itheima.service.cargo.ContractProductService;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/18 13:19
 */
@Service
public class ContractProductServiceImpl implements ContractProductService {
    @Override
    public PageInfo<ContractProduct> findByPage(ContractProductExample ContractProductExample, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<ContractProduct> findAll(ContractProductExample ContractProductExample) {
        return null;
    }

    @Override
    public ContractProduct findById(String id) {
        return null;
    }

    @Override
    public void save(ContractProduct contractProduct) {

    }

    @Override
    public void update(ContractProduct contractProduct) {

    }

    @Override
    public void delete(String id) {

    }
}
