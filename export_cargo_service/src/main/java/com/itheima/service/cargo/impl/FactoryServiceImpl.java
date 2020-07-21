package com.itheima.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.cargo.FactoryDao;
import com.itheima.domain.cargo.Factory;
import com.itheima.domain.cargo.FactoryExample;
import com.itheima.service.cargo.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/18 13:20
 */
@Service
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    private FactoryDao factoryDao;
    @Override
    public PageInfo<Factory> findByPage(FactoryExample factoryExample, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Factory> list = factoryDao.selectByExample(factoryExample);
        return new PageInfo<>(list);
    }

    @Override
    public List<Factory> findAll(FactoryExample factoryExample) {
        List<Factory> factoryList = factoryDao.selectByExample(factoryExample);
        return factoryList;
    }

    @Override
    public Factory findById(String id) {
        return factoryDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Factory factory) {
        factoryDao.insertSelective(factory);
    }

    @Override
    public void update(Factory factory) {
        factoryDao.updateByPrimaryKeySelective(factory);
    }

    @Override
    public void delete(String id) {
        factoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public String findIdByFactoryName(String factoryName) {
        FactoryExample factoryExample = new FactoryExample();
        factoryExample.createCriteria().andFactoryNameEqualTo(factoryName);
        List<Factory> factoryList = factoryDao.selectByExample(factoryExample);
        return (factoryList!=null &&factoryList.size()>0) ? factoryList.get(0).getId() : "";
    }
}
