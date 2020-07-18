package com.itheima.dao.cargo;

import com.itheima.domain.cargo.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author Jackson
 * @date 2020/7/18 10:35
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*xml")
public class CargoDaoTest {
    @Autowired
    private FactoryDao factoryDao;

    @Test
    public void insert(){
        Factory factory = new Factory();
        factory.setId("131");
        factory.setCreateTime(new Date());
        factory.setUpdateTime(new Date());
        factory.setFactoryName("草原工厂");
        factory.setAddress("牛栏山");
//        int insert = factoryDao.insert(factory);
//        System.out.println(insert);

    }
}
