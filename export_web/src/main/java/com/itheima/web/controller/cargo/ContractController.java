package com.itheima.web.controller.cargo;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.cargo.Contract;
import com.itheima.domain.cargo.ContractExample;
import com.itheima.service.cargo.ContractService;
import com.itheima.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jackson
 * @date 2020/7/18 13:35
 */
@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController {
    @Reference
    private ContractService contractService;


    /**
     * 1. 列表分页查询
     */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize){

        //1.1 构造查询条件
        ContractExample example = new ContractExample();
        // 根据create_time进行降序
        example.setOrderByClause("create_time desc");

        //1.2 查询条件对象
        ContractExample.Criteria criteria = example.createCriteria();
        //1.3 查询条件: 企业id
        criteria.andCompanyIdEqualTo(getLoginCompanyId());

        //1.2 调用service查询
        PageInfo<Contract> pageInfo =
                contractService.findByPage(example,pageNum,pageSize);
        //返回
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("cargo/contract/contract-list");
        return mv;
    }

    /**
     * 2.添加（1） 进入添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "cargo/contract/contract-add";
    }

    /**
     * 3. 添加/修改
     */
    @RequestMapping("/edit")
    public String edit(Contract contract){
        // 设置所属企业id、名称
        contract.setCompanyId(getLoginCompanyId());
        contract.setCompanyName(getCompanyName());
        // 判断
        if (StringUtils.isEmpty(contract.getId())){
            // 添加
            contractService.save(contract);
        } else {
            // 修改
            contractService.update(contract);
        }
        return "redirect:/cargo/contract/list.do";
    }

    /**
     * 4. 进入修改页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id){
        // 根据id查询
        Contract contract = contractService.findById(id);
        // 保存
        request.setAttribute("contract",contract);
        // 转发到页面
        return "cargo/contract/contract-update";
    }

    /**
     * 5. 删除
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        contractService.delete(id);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("/toView")
    public String toView(String id) {
        Contract contract = contractService.findById(id);
        request.setAttribute("contract",contract);
        return "cargo/contract/contract-view";
    }

    @RequestMapping("/submit")
    public String submit(String id) {
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(1);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

    @RequestMapping("/cancel")
    public String cancel(String id) {
        Contract contract = new Contract();
        contract.setId(id);
        contract.setState(0);
        contractService.update(contract);
        return "redirect:/cargo/contract/list.do";
    }

}
