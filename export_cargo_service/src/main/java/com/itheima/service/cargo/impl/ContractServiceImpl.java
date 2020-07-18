package com.itheima.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.cargo.ContractDao;
import com.itheima.domain.cargo.Contract;
import com.itheima.domain.cargo.ContractExample;
import com.itheima.service.cargo.ContractService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Jackson
 * @date 2020/7/18 13:19
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;
    @Override
    public PageInfo<Contract> findByPage(ContractExample contractExample, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Contract> contracts = contractDao.selectByExample(contractExample);
        return new PageInfo<>(contracts);
    }

    @Override
    public List<Contract> findAll(ContractExample contractExample) {
        return contractDao.selectByExample(contractExample);
    }

    @Override
    public Contract findById(String id) {
        return contractDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(Contract contract) {
        // 设置uuid作为主键
        contract.setId(UUID.randomUUID().toString());
        // 记录购销合同创建时间
        contract.setCreateTime(new Date());
        // 默认状态为草稿
        contract.setState(0);


        // 初始化： 总金额为0
        contract.setTotalAmount(0d);
        // 初始化： 货物数、附件数
        contract.setProNum(0);
        contract.setExtNum(0);
        contractDao.insertSelective(contract);
    }

    @Override
    public void update(Contract contract) {
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void delete(String id) {
        contractDao.deleteByPrimaryKey(id);
    }
}
