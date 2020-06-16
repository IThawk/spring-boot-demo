package com.gupaoedu.security.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gupaoedu.security.codec.CodecToolKit;
import com.gupaoedu.security.codec.MD5;
import com.gupaoedu.security.codec.RSA;
import com.gupaoedu.security.common.constans.FieldConstant;
import com.gupaoedu.security.common.constans.KeyConstant;
import com.gupaoedu.security.common.constans.SystemConstant;
import com.gupaoedu.security.common.utils.ToolUtils;
import com.gupaoedu.security.dao.MemberDao;
import com.gupaoedu.security.dao.RedisDao;
import com.gupaoedu.security.model.entity.Member;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.core.common.ResultMsg;
import javax.core.common.utils.RegexUtils;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by Tom on 2018/12/27.
 */
@Service
public class MemberService {

    @Autowired private MemberDao memberDao;
    @Autowired private RedisDao redisDao;

    public ResultMsg getByLoginName(String local, String loginName, String enc){
        //验证参数
        if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(enc)){
            JSONObject error = new JSONObject();
            if(StringUtils.isEmpty(loginName)){
                error.put("loginName", "登录账号未填写");
            }
            if(StringUtils.isEmpty(enc)){
                error.put("enc", "授权码未填写");
            }
            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "参数不完整",error);
        }

        //检查授权码是否正确
        if(!CodecToolKit.genEnc(loginName).equals(enc)){
            JSONObject error = new JSONObject();
            error.put("enc", "授权码错误");
            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "授权码错误",error);
        }

        //判断用户名是否已经注册
        Member member = memberDao.selectByLoginName(loginName);
        if(null == member){
            return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_SUCCESS, "可以注册的账号");
        }

        JSONObject result = JSONObject.parseObject(JSON.toJSONString(member));
        result.remove("loginPass");
        return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_ERROR, "账号已被注册",result);
    }


    public ResultMsg<?> login(String loginName, String loginPass, String enc) {

        //验证参数
        if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPass)){
            JSONObject error = new JSONObject();
            if(StringUtils.isEmpty(loginName)){
                error.put("loginName", "登录账号未填写");
            }
            if(StringUtils.isEmpty(loginPass)){
                error.put("loginPass", "密码未填写");
            }
//            if(StringUtils.isEmpty(enc)){
//                error.put("enc", "授权码未填写");
//            }
            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "参数不完整",error);
        }

        //检查授权码是否正确
//        if(!CodecToolKit.genEnc(loginName+loginPass).equals(enc)){
//            JSONObject error = new JSONObject();
//            error.put("enc", "授权码错误");
//            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "授权码错误",error);
//        }

        try {

            Member member = memberDao.selectByLoginName(loginName);

            if(null == member){
                JSONObject error = new JSONObject();
                error.put("loginName", "用户不存在");
                return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_ERROR, "用户不存在",error);
            }


            String inputPass = new String(RSA.privateDecrypt(Base64.getUrlDecoder().decode(loginPass),
                    KeyConstant.PRIVATE_KEY));
            if(!member.getLoginPass().equals(inputPass)){
                JSONObject error = new JSONObject();
                error.put("loginPass", "密码不正确");
                return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_ERROR, "密码不正确",error);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg<Object>(SystemConstant.RESULT_STATUS_ERROR, "非法请求");
        }

        JSONObject result = new JSONObject();
        result.put("loginName",loginName);
        String token = MD5.encode(ToolUtils.systime() + ToolUtils.randomNo(4));
        result.put("token", token);
        redisDao.putKey(token);
        return new ResultMsg(SystemConstant.RESULT_STATUS_SUCCESS,"登录成功",result);
    }

    public ResultMsg<?> logout(Object o) {
        return new ResultMsg(SystemConstant.RESULT_STATUS_SUCCESS,"注销成功");
    }
}
