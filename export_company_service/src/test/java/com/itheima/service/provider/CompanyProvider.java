package com.itheima.service.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Jackson
 * @date 2020/7/17 18:15
 */
public class CompanyProvider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        context.start();
        System.in.read();
    }

}
