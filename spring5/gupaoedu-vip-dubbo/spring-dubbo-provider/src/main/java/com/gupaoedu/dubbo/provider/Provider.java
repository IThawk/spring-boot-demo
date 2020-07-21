package com.gupaoedu.dubbo.provider;

import com.gupaoedu.dubbo.api.IRpcHello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author IThawk
 * @version V1.0
 * @description:
 * @date 2020-07-21 20:31
 */
public class Provider {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        applicationContext.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
