package com.itheima.web.controller.cargo;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.*;
import com.itheima.service.cargo.ContractService;
import com.itheima.service.cargo.ExportProductService;
import com.itheima.service.cargo.ExportService;
import com.itheima.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/21 17:39
 */
@Controller
@RequestMapping("/cargo/export")
public class ExportController extends BaseController {

    @Reference
    private ContractService contractService;

    @Reference
    private ExportService exportService;

    @Reference
    private ExportProductService exportProductService;

    /**
     * 合同列表（已上报）
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/contractList")
    public String contractList(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize){
        ContractExample contractExample = new ContractExample();
        ContractExample.Criteria criteria = contractExample.createCriteria();
        criteria.andCompanyIdEqualTo(getLoginCompanyId());
        criteria.andStateEqualTo(1);

        PageInfo<Contract> pageInfo = contractService.findByPage(contractExample, pageNum, pageSize);
        request.setAttribute("pageInfo",pageInfo);
        return "cargo/export/export-contractList";
    }

    @RequestMapping("/toExport")
    public String toExport(String id){
        request.setAttribute("id",id);
        return "cargo/export/export-toExport";
    }

    /**
     * 报运单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "5") int pageSize){
        ExportExample exportExample = new ExportExample();
        exportExample.createCriteria().andCompanyIdEqualTo(getLoginCompanyId());
        PageInfo<Export> pageInfo = exportService.findByPage(exportExample, pageNum, pageSize);
        request.setAttribute("pageInfo",pageInfo);
        return "cargo/export/export-list";
    }

    /**
     * 增加或修改报运单
     * @param export
     * @return
     */
    @RequestMapping("/edit")
    public String edit(Export export){
        export.setCompanyId(getLoginCompanyId());
        export.setCompanyName(getCompanyName());
        if(StringUtils.isEmpty(export.getId())){
            exportService.save(export);
        }else {
            exportService.update(export);
        }
        return "redirect:/cargo/export/list";
    }

    /**
     * 更新报运单页面
     * @param id
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        Export export = exportService.findById(id);
        ExportProductExample exportProductExample = new ExportProductExample();
        exportProductExample.createCriteria().andExportIdEqualTo(id);
        List<ExportProduct> eps = exportProductService.findAll(exportProductExample);
        request.setAttribute("export",export);
        request.setAttribute("eps",eps);

        return "cargo/export/export-update";
    }

    /**
     * 查看报运单
     * @param id
     * @return
     */
    @RequestMapping("/toView")
    public String toView(String id){
        Export export = exportService.findById(id);
        request.setAttribute("export",export);
        return "cargo/export/export-view";
    }
}
