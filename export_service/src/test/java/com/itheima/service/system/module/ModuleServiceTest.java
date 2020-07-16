package com.itheima.service.system.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Jackson
 * @date 2020/7/13 17:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class ModuleServiceTest {
    @Autowired
    private ModuleService moduleService;
    @Test
    public void findModuleByRoleIdTest(){
        System.out.println(moduleService.findModuleByRoleId("4028a1c34ec2e5c8014ec2ebf8430001"));
    }
}
