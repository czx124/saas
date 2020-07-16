package com.itheima.web.controller.system.dept;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.dept.Dept;
import com.itheima.service.system.dept.DeptService;
import com.itheima.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jackson
 */
@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/list")
    public ModelAndView findDeptList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize) {
        String companyId = getLoginCompanyId();
        PageInfo<Dept> pageInfo = deptService.findAll(companyId, pageNum, pageSize);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/dept/dept-list");
        mv.addObject("pageInfo", pageInfo);
        return mv;
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        String companyId = getLoginCompanyId();
        List<Dept> deptList = deptService.findDeptList(companyId);
        model.addAttribute("deptList", deptList);
        return "system/dept/dept-add";
    }

    @RequestMapping("/edit")
    public String edit(Dept dept) {
        dept.setCompanyId(getLoginCompanyId());
        dept.setCompanyName(getCompanyName());
        if (dept.getId().isEmpty()) {
            deptService.save(dept);
        } else {
            deptService.update(dept);
        }
        return "redirect:/system/dept/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model) {
        String companyId = getLoginCompanyId();
        List<Dept> deptList = deptService.findDeptList(companyId);
        System.out.println(deptList);
        Dept dept = deptService.findById(id);
        model.addAttribute("dept", dept);
        model.addAttribute("deptList", deptList);
        return "system/dept/dept-update";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, String> delete(String id) {
        Map<String, String> result = new HashMap<>(16);
        boolean flag = deptService.delete(id);
        if (flag) {
            result.put("message", "删除成功");
        } else {
            result.put("message", "删除失败！您选择的部门有子部门，不能删除");
        }
        return result;
    }
}
