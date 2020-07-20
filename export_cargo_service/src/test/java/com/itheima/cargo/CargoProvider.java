package com.itheima.cargo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Jackson
 * @date 2020/7/18 13:30
 */
public class CargoProvider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        context.start();
        System.in.read();
    }
}
