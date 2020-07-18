package com.itheima.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.Factory;
import com.itheima.domain.cargo.FactoryExample;
import com.itheima.service.cargo.FactoryService;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/18 13:20
 */
@Service
public class FactoryServiceImpl implements FactoryService {
    @Override
    public PageInfo<Factory> findByPage(FactoryExample factoryExample, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Factory> findAll(FactoryExample factoryExample) {
        return null;
    }

    @Override
    public Factory findById(String id) {
        return null;
    }

    @Override
    public void save(Factory factory) {

    }

    @Override
    public void update(Factory factory) {

    }

    @Override
    public void delete(String id) {

    }
}
