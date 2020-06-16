package com.gupaoedu.security;

import com.gupaoedu.security.codec.RSA;
import com.gupaoedu.security.common.constans.KeyConstant;
import com.gupaoedu.security.common.constans.SystemConstant;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Base64;

/**
 * Created by Tom on 2018/12/30.
 */
public class LoginCertTest {

    public static void main(String[] args) {
        try{
            String password = "123456";
            String encodeValue = Base64.getUrlEncoder().encodeToString(RSA.publicEncrypt(password, KeyConstant.PUBLIC_KEY));
            System.out.println("加密后的密码 ： " + encodeValue);

//            String inputPass = "oe5S/ZOMwnwuoD5/jkpcJOg1yQn2ANlOa3vkSnnZ86uX0JRXTtW7NtyBY3Bc+8uCjpdvBdnhVJmE" +
//                    "IRXEYVlIfOO+pBtkRcn4whgGW1QLq9uRpjFKFka4422/0rGlra6Czzfd3+IdiICpUJ3Roeyw1han" +
//                    "WvYGqlfRkRKKQ34svcw=";
//            String resutl = new String(RSA.privateDecrypt(new BASE64Decoder().decodeBuffer(inputPass),
//                    KeyConstant.PRIVATE_KEY));
//            System.out.println("解密:" + resutl);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
