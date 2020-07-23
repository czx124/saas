package com.itheima.service.cargo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.cargo.*;
import com.itheima.domain.cargo.*;
import com.itheima.service.cargo.ContractProductService;
import com.itheima.service.cargo.ExportService;
import com.itheima.service.cargo.ExtCproductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author Jackson
 * @date 2020/7/21 19:27
 */
@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private ExportDao exportDao;
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ExportProductDao exportProductDao;
    @Autowired
    private ContractProductDao contractProductDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ExtEproductDao extEproductDao;
    @Override
    public Export findById(String id) {
        return exportDao.selectByPrimaryKey(id);
    }

    /**
     * 保存报运单
     * @param export
     */
    @Override
    public void save(Export export) {
        //封装对象
        export.setId(UUID.randomUUID().toString());
        export.setCreateTime(new Date());
        export.setInputDate(new Date());
        export.setState(0);
        //获取合同id
        String contractIds = export.getContractIds();
        String[] idsArr = contractIds.split(",");
        //修改合同状态
        for (String id : idsArr){
            Contract contract = contractDao.selectByPrimaryKey(id);
            contract.setState(2);
            contractDao.updateByPrimaryKeySelective(contract);
        }

        //转成list集合，后面的条件对象要用到
        List<String> list = Arrays.asList(idsArr);
        Map<String,String> map = new HashMap<>();
        ContractProductExample contractProductExample = new ContractProductExample();
        contractProductExample.createCriteria().andContractIdIn(list);
        List<ContractProduct> contractProducts = contractProductDao.selectByExample(contractProductExample);
        //添加商品
        if(contractProducts!=null && contractProducts.size()>0){
            for (ContractProduct contractProduct : contractProducts){
                //封装exportProduct对象
                ExportProduct exportProduct = new ExportProduct();
                BeanUtils.copyProperties(contractProduct,exportProduct);
                exportProduct.setId(UUID.randomUUID().toString());
                exportProduct.setExportId(export.getId());
                exportProductDao.insertSelective(exportProduct);
                map.put(contractProduct.getId(),exportProduct.getId());

            }
        }

        //添加商品附件
        ExtCproductExample extCproductExample = new ExtCproductExample();
        extCproductExample.createCriteria().andContractIdIn(list);
        List<ExtCproduct> extCproducts = extCproductDao.selectByExample(extCproductExample);
        for (ExtCproduct extCproduct : extCproducts){
            //封装ExtEproduct
            ExtEproduct extEproduct = new ExtEproduct();
            BeanUtils.copyProperties(extCproduct,extEproduct);
            extEproduct.setId(UUID.randomUUID().toString());
            extEproduct.setExportId(export.getId());
            String contractProductId = extCproduct.getContractProductId();
            String exportProductId = map.get(contractProductId);
            extEproduct.setExportProductId(exportProductId);
            extEproductDao.insertSelective(extEproduct);
        }
        //设置商品数量和商品附件数量
        export.setProNum(contractProducts.size());
        export.setExtNum(extCproducts.size());
        exportDao.insertSelective(export);

    }

    @Override
    public void update(Export export) {
        //更新export报运单
        exportDao.updateByPrimaryKeySelective(export);
        //更新商品
        List<ExportProduct> exportProducts = export.getExportProducts();
        for (ExportProduct exportProduct : exportProducts) {
            exportProductDao.updateByPrimaryKeySelective(exportProduct);
        }
    }

    @Override
    public void delete(String id) {
        exportDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Export> findByPage(ExportExample example, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Export> list = exportDao.selectByExample(example);
        return new PageInfo<>(list);
    }

}
