package com.itheima.web.controller.system.role;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.module.Module;
import com.itheima.domain.system.role.Role;
import com.itheima.service.system.module.ModuleService;
import com.itheima.service.system.dept.DeptService;
import com.itheima.service.system.role.RoleService;
import com.itheima.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jackson
 * @date 2020/7/13 13:22
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ModuleService moduleService;

    /**
     * 角色列表分页
     */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize) {
        //初始化当前登录用户所属的企业ID为1
        String companyId = getLoginCompanyId();

        //1.调用service查询部门列表
        PageInfo pageInfo = roleService.findByPage(companyId, pageNum, pageSize);
        //2.返回
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("system/role/role-list");
        return mv;
    }

    /**
     * 进入新增角色页面
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "system/role/role-add";
    }

    /**
     * 新增角色
     */
    @RequestMapping("/edit")
    public String edit(Role role) {
        String company = getLoginCompanyId();
        String companyName = getCompanyName();
        role.setCompanyId(company);
        role.setCompanyName(companyName);

        //1.判断是否具有id属性
        if(StringUtils.isEmpty(role.getId())) {
            //2.没有id，保存
            roleService.save(role);
        }else{
            //3.具有id，更新
            roleService.update(role);
        }
        return "redirect:/system/role/list.do";
    }

    /**
     * 进入到修改界面
     *  1.获取到id，根据id进行查询
     *  2.保存查询结果到request域中
     *  3.跳转到修改界面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(String id){
        Role role = roleService.findById(id);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/role/role-update");
        mv.addObject("role",role);
        return mv;
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,String> delete(String id) {
        Map<String,String> result = new HashMap<>();
        boolean flag = roleService.delete(id);
        if(flag){
            result.put("message","删除成功");
        }else {
            result.put("message","您选择的角色有关联其他数据表，请重新选择删除的角色");
        }

        return result;
        //跳转到修改界面
        //return "redirect:/system/role/list.do";
    }

    @RequestMapping("/roleModule")
    public ModelAndView roleModule(String roleId) {
        Role role = roleService.findById(roleId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/role/role-module");
        mv.addObject("role",role);
        //跳转到修改界面
        return mv;
    }

    @RequestMapping("/getZtreeNodes")
    @ResponseBody
    public List<Map<String,Object>> getZtreeNodes(String roleId) {
        List<Module> moduleList = moduleService.findAll();
        List<Module> roleModules = moduleService.findModuleByRoleId(roleId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Module module:moduleList){
            Map<String, Object> map = new HashMap<>();
            map.put("id",module.getId());
            map.put("pId", module.getParentId());
            map.put("name", module.getName());
            map.put("open", true);
            if(roleModules.contains(module)){
                map.put("checked", true);
            }
            list.add(map);
        }

        list.forEach(System.out::println);

        return list;
    }

    @RequestMapping("/updateRoleModule")
    public String updateRoleModule(String roleId,String moduleIds){
        roleService.updateRoleModule(roleId, moduleIds);
        return "redirect:/system/role/list";
    }
}
