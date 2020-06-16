package com.gupaoedu.security;

import com.gupaoedu.security.codec.CodecToolKit;
import com.gupaoedu.security.codec.MD5;
import com.gupaoedu.security.codec.RSA;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigDecimal;
import java.security.Key;
import java.util.Map;

public class CodecTest {

    public static void main(String[] args) {

        try {

            BASE64Encoder base64Encoder = new BASE64Encoder();
            BASE64Decoder base64Decoder = new BASE64Decoder();

            //RSA生成一对秘钥
            Map<String, Key> keys = RSA.initKey();
            String publicKey = RSA.getPublicKey(keys);
            String privateKey = RSA.getPrivateKey(keys);
            System.out.println("公钥: \n\r" + publicKey);
            System.out.println("私钥： \n\r" + privateKey);


//            //RSA加、解密
            String rsaInputValue = "我是Tom老师";
//
//            String encodeValue = base64Encoder.encodeBuffer(RSA.publicEncrypt(rsaInputValue,publicKey));
//            System.out.println("公钥加密 ： " + encodeValue);
//            byte [] decodeValue = RSA.privateDecrypt(base64Decoder.decodeBuffer(encodeValue),privateKey);
//            System.out.println("私钥解密 ： " + new String(decodeValue));


//            //RSA签名
//            String signEncValue = base64Encoder.encodeBuffer(RSA.privateEncrypt(rsaInputValue.getBytes(),privateKey));
//            System.out.println("私钥签名后的数据：" + signEncValue);
//            byte [] signDecValue = RSA.publicDecrypt(base64Decoder.decodeBuffer(signEncValue),publicKey);
//            System.out.println("公钥解签后的数据：" + new String(signDecValue));


//            //Base64操作
//            String base64InputValue = "咕泡学院";
//            String encValue = base64Encoder.encodeBuffer(base64InputValue.getBytes());
//            System.out.println("Base64 Enc:" + encValue);
//            byte[] decValue = base64Decoder.decodeBuffer(encValue);
//            System.out.println("Base64 Dec:" + new String(decValue));
//
//            String md5InputValue = "我是咕泡学院Tom老师";
//            System.out.println("MD5 Enc：" +MD5.encode(md5InputValue));


            BigDecimal bigAmount = new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP);
            String enc = CodecToolKit.genEnc("P201812300001441277" + bigAmount.toString());
            System.out.println("混淆校验值：" + enc);

//            String result = new String(base64Decoder.decodeBuffer("ub7F3dGn1Lo="),"gb2312");
//            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
