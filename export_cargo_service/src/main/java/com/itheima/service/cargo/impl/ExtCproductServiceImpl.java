package com.itheima.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.cargo.ContractDao;
import com.itheima.dao.cargo.ExtCproductDao;
import com.itheima.domain.cargo.Contract;
import com.itheima.domain.cargo.ExtCproduct;
import com.itheima.domain.cargo.ExtCproductExample;
import com.itheima.service.cargo.ExtCproductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author Jackson
 * @date 2020/7/18 13:20
 */
@Service
public class ExtCproductServiceImpl implements ExtCproductService {
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ContractDao contractDao;
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
        extCproduct.setId(UUID.randomUUID().toString());
//        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
//        double amount = 0d;
//        if(extCproduct.getCnumber()!=null && extCproduct.getPrice()!=null){
//            amount = extCproduct.getCnumber()*extCproduct.getPrice();
//        }
//        extCproduct.setAmount(amount);
//        extCproductDao.insertSelective(extCproduct);
//        contract.setExtNum(contract.getExtNum()+1);
//        contract.setTotalAmount(contract.getTotalAmount()+amount);
//        contractDao.updateByPrimaryKeySelective(contract);

    }

    @Override
    public void update(ExtCproduct extCproduct) {

    }

    @Override
    public void delete(String id) {

    }
}
