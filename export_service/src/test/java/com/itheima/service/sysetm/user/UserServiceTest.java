package com.itheima.service.sysetm.user;

import com.github.pagehelper.PageInfo;
import com.itheima.dao.system.user.UserDao;
import com.itheima.domain.system.module.Module;
import com.itheima.domain.system.user.User;
import com.itheima.service.system.module.ModuleService;
import com.itheima.service.system.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Jackson
 * @date 2020/7/13 11:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserDao userDao;
    @Test
    public void findByPage() {
        PageInfo<User> byPage = userService.findByPage("1", 1, 3);
        System.out.println(byPage);
    }

    @Test
    public void findAll() {
        List<User> byPage = userService.findAll("1");
        System.out.println(byPage);
    }

    @Test
    public void save() {
        User user = new User();
        userService.save(user);
    }

    @Test
    public void update() {
        User user = new User();
        userService.update(user);
    }

    @Test
    public void delete() {
        userService.delete("1");

    }

    @Test
    public void findById() {
        User user = userService.findById("3");
        System.out.println(user);
    }

    @Test
    public void findModulesByUid(){
        List<Module> modulesByUid = moduleService.findModulesByUid("0d39fa39-5e4d-47ac-ae01-acedd82904bb");
        System.out.println(modulesByUid);
    }


}