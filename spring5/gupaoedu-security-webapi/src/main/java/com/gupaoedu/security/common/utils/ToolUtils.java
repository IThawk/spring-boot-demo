package com.gupaoedu.security.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by Tom on 2018/12/30.
 */
public class ToolUtils {

    /**
     * 随机生成微信号
     * @return
     */
    public static String randomNo(int num){
        char [] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toLowerCase().toCharArray();
        //微信号自动生成规则，wx_开头加上10位数字字组合
        StringBuffer sb = new StringBuffer();
        for(int c = 0;c < num;c ++){
            int i = new Random().nextInt(chars.length);
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static long systime(){
        return System.currentTimeMillis();
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

}
