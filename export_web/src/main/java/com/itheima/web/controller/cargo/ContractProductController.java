package com.itheima.web.controller.cargo;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.ContractProduct;
import com.itheima.domain.cargo.ContractProductExample;
import com.itheima.domain.cargo.Factory;
import com.itheima.domain.cargo.FactoryExample;
import com.itheima.service.cargo.ContractProductService;
import com.itheima.service.cargo.FactoryService;
import com.itheima.web.controller.BaseController;
import com.itheima.web.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/18 20:47
 */
@Controller
@RequestMapping("/cargo/contractProduct")
public class ContractProductController extends BaseController {
    @Reference
    private ContractProductService contractProductService;
    @Reference
    private FactoryService factoryService;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    @RequestMapping("/list")
    public String contractList(String contractId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        //查询生产厂家
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria factoryExampleCriteria = factoryExample.createCriteria();
        factoryExampleCriteria.andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList", factoryList);

        //根据购销合同id查询购销合同下的所有货物
        ContractProductExample contractProductExample = new ContractProductExample();
    ContractProductExample.Criteria contractProductExampleCriteria = contractProductExample.createCriteria();
        contractProductExampleCriteria.andContractIdEqualTo(contractId);

    PageInfo<ContractProduct> pageInfo = contractProductService.findByPage(contractProductExample, pageNum, pageSize);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("contractId", contractId);
        return "cargo/product/product-list";
}

    /**
     * 保存，修改
     */

    @RequestMapping("/edit")
    public String edit(MultipartFile productPhoto, ContractProduct contractProduct) {
        contractProduct.setCompanyId(getLoginCompanyId());
        contractProduct.setCompanyName(getCompanyName());
        if (StringUtils.isEmpty(contractProduct.getId())) {
            try {
                String fullFileUrl = "http://"+fileUploadUtil.upload(productPhoto);
                contractProduct.setProductImage(fullFileUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            contractProductService.save(contractProduct);
        } else {
            contractProductService.update(contractProduct);
        }
        return "redirect:/cargo/contractProduct/list?contractId=" +
                contractProduct.getContractId();

    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        ContractProduct contractProduct = contractProductService.findById(id);
        FactoryExample factoryExample = new FactoryExample();
        //设置条件
        factoryExample.createCriteria().andCtypeEqualTo("货物");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        request.setAttribute("contractProduct",contractProduct);
        request.setAttribute("factoryList",factoryList);
        return "cargo/product/product-update";
    }

    @RequestMapping("/delete")
    public String delete(String id,String contractId){
        contractProductService.delete(id);
        return "redirect:/cargo/contractProduct/list?contractId="+contractId;
    }


}
