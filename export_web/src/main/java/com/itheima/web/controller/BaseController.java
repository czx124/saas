package com.itheima.web.controller;

import com.itheima.domain.system.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Jackson
 * @date 2020/7/12 16:42
 */
@SuppressWarnings("ALL")
@Controller
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;

    public String getLoginCompanyId() {
        return getLoginUser().getCompanyId();
    }

    public String getCompanyName() {
        return getLoginUser().getCompanyName();
    }

    public User getLoginUser() {
        return (User) session.getAttribute("user");
    }
}
