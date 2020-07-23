package com.itheima.web.controller;

import com.itheima.domain.system.module.Module;
import com.itheima.domain.system.user.User;
import com.itheima.service.system.module.ModuleService;
import com.itheima.service.system.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/14 14:01
 */
@Controller

public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;

    @RequestMapping("/weixinlogin")
    public String wxLogin(String code){
        User user = userService.saveWxLogin(code);
        if(user==null){
            request.setAttribute("error","微信登录失败");
            return "forward:/login.jsp";
        }

        session.setAttribute("user", user);
        List<Module> modules = moduleService.findModulesByUid(user.getId());
        session.setAttribute("modules",modules);
        return "/home/main";
    }

//    @RequestMapping("/login")
//    public String login(String email, String password) {
//        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
//            return "forward:/login.jsp";
//        }
//
//        User user = userService.findByEmail(email);
//        if (user != null && user.getPassword().equals(password)) {
//            session.setAttribute("user", user);
//            List<Module> modules = moduleService.findModulesByUid(user.getId());
//            session.setAttribute("modules",modules);
//            return "/home/main";
//        } else {
//            request.setAttribute("error", "用户名或密码错误");
//            return "forward:/login.jsp";
//        }
//    }

    @RequestMapping("/login")
    public String login(String email,String password){
        try {
            //获取subject对象
            Subject subject = SecurityUtils.getSubject();
            //构造用户名和密码
            UsernamePasswordToken upToken = new UsernamePasswordToken(email, password);
            subject.login(upToken);
            //通过shiro获取用户对象，保存到session中
            User user = (User) subject.getPrincipal();
            session.setAttribute("user",user);
            //获取动态菜单数据
            List<Module> modules = moduleService.findModulesByUid(user.getId());
            session.setAttribute("modules",modules);
            //跳到成功页面
            return "home/main";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            request.setAttribute("error", "用户名或密码错误");
            return "forward:login.jsp";
        }

    }

    @RequestMapping("/home")
    public String home() {
        return "/home/home";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        session.removeAttribute("user");
        session.invalidate();
        return "forward:/login.jsp";
    }
}
