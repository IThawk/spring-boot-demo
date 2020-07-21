package com.gupaoedu.dubbo.consumer;

import com.gupaoedu.dubbo.api.IRpcHello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author IThawk
 * @version V1.0
 * @description:
 * @date 2020-07-21 20:31
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        applicationContext.start();

        IRpcHello iRpcHello = applicationContext.getBean("iRpcHello", IRpcHello.class);
        System.out.println(iRpcHello.hello("dddd"));

    }
}
