package com.itheima.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.ExtCproduct;
import com.itheima.domain.cargo.ExtCproductExample;
import com.itheima.service.cargo.ExtCproductService;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/18 13:20
 */
@Service
public class ExtCproductServiceImpl implements ExtCproductService {
    @Override
    public PageInfo<ExtCproduct> findByPage(ExtCproductExample extCproductExample, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<ExtCproduct> findAll(ExtCproductExample extCproductExample) {
        return null;
    }

    @Override
    public ExtCproduct findById(String id) {
        return null;
    }

    @Override
    public void save(ExtCproduct extCproduct) {

    }

    @Override
    public void update(ExtCproduct extCproduct) {

    }

    @Override
    public void delete(String id) {

    }
}
