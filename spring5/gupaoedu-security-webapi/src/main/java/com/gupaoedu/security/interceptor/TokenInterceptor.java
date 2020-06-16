package com.gupaoedu.security.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.gupaoedu.security.codec.MD5;
import com.gupaoedu.security.common.constans.SystemConstant;
import com.gupaoedu.security.common.utils.ToolUtils;
import com.gupaoedu.security.dao.RedisDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.core.common.ResultMsg;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final List<String> extendUrls = new ArrayList<String>();
    @Autowired private RedisDao redisDao;

    static {
        extendUrls.add("/system/login.json");
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object object) throws Exception {
         resp.setCharacterEncoding("UTF-8");
//        HandlerMethod handlerMethod = (HandlerMethod) object;

//        String ip = ToolUtils.getIp(req);
        String uri = req.getRequestURI().replaceAll(req.getContextPath(),"");

        if(extendUrls.contains(uri)){
            return true;
        }
        String token = req.getParameter("token");
        if(null == token || "".equals(token)){
            resp.getWriter().print(JSONObject.toJSONString(new ResultMsg<Object>(SystemConstant.RESULT_STATUS_ERROR, "非法请求")));
            return false;
        }
        if(!redisDao.hasKey(token)){
            resp.getWriter().print(JSONObject.toJSONString(new ResultMsg<Object>(SystemConstant.RESULT_STATUS_ERROR, "请求已过期")));
            return false;
        }
        return true;

    }



}