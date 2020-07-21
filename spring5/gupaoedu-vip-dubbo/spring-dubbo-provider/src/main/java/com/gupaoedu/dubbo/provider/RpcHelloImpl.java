package com.gupaoedu.dubbo.provider;

import com.gupaoedu.dubbo.api.IRpcHello;

public class RpcHelloImpl implements IRpcHello {

    @Override
    public String hello(String name) {
        return "Hello " + name + ",你下面看到的消息来自于远方!";
    }

}
