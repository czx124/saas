package com.itheima.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.cargo.ContractDao;
import com.itheima.dao.cargo.ContractProductDao;
import com.itheima.dao.cargo.ExtCproductDao;
import com.itheima.domain.cargo.*;
import com.itheima.service.cargo.ContractProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * @author Jackson
 * @date 2020/7/18 13:19
 */
@Service
public class ContractProductServiceImpl implements ContractProductService {
    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ExtCproductDao extCproductDao;
    @Override
    public PageInfo<ContractProduct> findByPage(ContractProductExample contractProductExample, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ContractProduct> contractProducts = contractProductDao.selectByExample(contractProductExample);
        return new PageInfo<>(contractProducts);
    }

    @Override
    public List<ContractProduct> findAll(ContractProductExample ContractProductExample) {
        return contractProductDao.selectByExample(ContractProductExample);
    }

    @Override
    public ContractProduct findById(String id) {
        return contractProductDao.selectByPrimaryKey(id);
    }

    @Override
    public void save(ContractProduct contractProduct) {
        contractProduct.setId(UUID.randomUUID().toString());
        double amount = 0d;
        if(contractProduct.getCnumber()!=null && contractProduct.getPrice()!=null){
            amount = contractProduct.getCnumber()*contractProduct.getPrice();
        }
        contractProduct.setAmount(amount);
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        contract.setProNum(contract.getProNum()+1);
        contract.setTotalAmount(contract.getTotalAmount()+amount);
        contractProductDao.insertSelective(contractProduct);
        contractDao.updateByPrimaryKeySelective(contract);
    }

    @Override
    public void update(ContractProduct contractProduct) {
        double amount = 0d;
        if(contractProduct.getCnumber()!=null && contractProduct.getPrice()!=null){
            amount = contractProduct.getCnumber()*contractProduct.getPrice();
        }
        contractProduct.setAmount(amount);
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        //修改前金额
        Double oldAmount = contractProductDao.selectByPrimaryKey(contractProduct.getId()).getAmount();
        //更新总金额
        contract.setTotalAmount(contract.getTotalAmount()-oldAmount+amount);
        contractDao.updateByPrimaryKeySelective(contract);
        contractProductDao.updateByPrimaryKeySelective(contractProduct);
    }

    @Override
    public void delete(String id) {
        //更新contract总金额:减掉货物金额及其附件金额
        ContractProduct contractProduct = contractProductDao.selectByPrimaryKey(id);
        Double proAmount = contractProduct.getAmount();
        Contract contract = contractDao.selectByPrimaryKey(contractProduct.getContractId());
        double extProAmount = 0d;
        ExtCproductExample extCproductExample = new ExtCproductExample();
        extCproductExample.createCriteria().andContractProductIdEqualTo(contractProduct.getId());
        List<ExtCproduct> extCproductList = extCproductDao.selectByExample(extCproductExample);
        for (ExtCproduct extCproduct:extCproductList){
            extProAmount += extCproduct.getAmount();
            //删除附件
            extCproductDao.deleteByPrimaryKey(extCproduct.getId());
        }
        //更新合同
        contract.setProNum(contract.getProNum()-1);
        contract.setExtNum(contract.getExtNum()-extCproductList.size());
        contract.setTotalAmount(contract.getTotalAmount()-proAmount-extProAmount);
        contractDao.updateByPrimaryKeySelective(contract);
        //删除货物
        contractProductDao.deleteByPrimaryKey(id);
    }
}
