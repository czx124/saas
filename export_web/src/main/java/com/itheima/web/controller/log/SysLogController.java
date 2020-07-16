package com.itheima.web.controller.log;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.system.log.SysLog;
import com.itheima.service.log.SysLogService;
import com.itheima.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jackson
 * @date 2020/7/14 20:28
 */
@Controller
@RequestMapping("/system/log")
public class SysLogController extends BaseController {
    @Autowired
    private SysLogService sysLogService;
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "5") int pageSize){
        String companyId = getLoginCompanyId();
        PageInfo<SysLog> pageInfo = sysLogService.findAll(companyId, pageNum, pageSize);
        request.setAttribute("pageInfo",pageInfo);
        return "system/log/log-list";
    }
}
