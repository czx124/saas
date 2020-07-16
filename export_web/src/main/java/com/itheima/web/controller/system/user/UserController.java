package com.itheima.web.controller.system.user;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.dept.Dept;
import com.itheima.domain.system.role.Role;
import com.itheima.domain.system.user.User;
import com.itheima.service.system.role.RoleService;
import com.itheima.service.system.user.UserService;
import com.itheima.service.system.dept.DeptService;
import com.itheima.web.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jackson
 * @date 2020/7/13 10:09
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;

    //用户列表分页
    @RequestMapping("/list")
    @RequiresPermissions("用户管理")
    public ModelAndView list(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int PageSize) {
        //硬编码方式实现授权
//        Subject subject = SecurityUtils.getSubject();
//        subject.checkPermission("部门管理");
        //1.调用service查询用户列表
        PageInfo<User> pageInfo = userService.findByPage(getLoginCompanyId(), pageNum, PageSize);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/user/user-list");
        mv.addObject("pageInfo", pageInfo);
        //3.跳转到对象的页面
        return mv;
    }

    /**
     * 进入新增用户页面
     */
    @RequestMapping("/toAdd")
    public ModelAndView toAdd() {
        String companyId = getLoginCompanyId();
        ModelAndView mv = new ModelAndView();
        //1.查询所有部门
        List<Dept> deptList = deptService.findDeptList(companyId);
        mv.setViewName("system/user/user-add");
        mv.addObject("deptList",deptList);
        return mv;
    }

    /**
     * 新增或更新用户
     */
    @RequestMapping("/edit")
    public String edit(User user) {
        String company = getLoginCompanyId();
        String companyName = getCompanyName();
        user.setCompanyId(company);
        user.setCompanyName(companyName);

        //1.判断是否具有id属性
        if(StringUtils.isEmpty(user.getId())) {
            //2.用户id为空，执行保存
            userService.save(user);
        }else{
            //3.用户id不为空，执行更新
            userService.update(user);
        }
        return "redirect:/system/user/list.do";
    }

    /**
     * 进入到修改界面
     */
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(String id){
        String companyId = getLoginCompanyId();
        User user = userService.findById(id);
        List<Dept> deptList = deptService.findDeptList(companyId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/user/user-update");
        mv.addObject("user",user);
        mv.addObject("deptList", deptList);
        return mv;
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String,String> delete(String id) {
        Map<String, String> result = new HashMap<>();
        boolean flag = userService.delete(id);
        if(flag){
            result.put("message", "删除成功");
        }else {
            result.put("message", "您选择的用户有关联到其他数据表，请重新选择要删除的用户");
        }
        //跳转到修改界面
        return result;
    }

    @RequestMapping("/roleList")
    public ModelAndView roleList(String id) {
        List<Role> roleList = roleService.findAll(getLoginCompanyId());
        ModelAndView mv = new ModelAndView();
        User user = userService.findById(id);
        List<Role> userRole = roleService.findByUid(id);
        mv.addObject("roleList",roleList);
        mv.addObject("user",user);
        mv.addObject("userRole",userRole);
        mv.setViewName("system/user/user-role");
        //跳转到修改界面
        return mv;
    }

    @RequestMapping("/changeRole")
    public String changeRole(String userId,String[] roleIds){
        userService.changeRole(userId, roleIds);
        return "redirect:/system/user/list";
    }


}
