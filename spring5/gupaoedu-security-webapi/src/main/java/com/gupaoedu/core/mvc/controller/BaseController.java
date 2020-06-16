package com.gupaoedu.core.mvc.controller;

import com.gupaoedu.security.common.constans.SystemConstant;
import org.springframework.util.StringUtils;

import javax.core.common.utils.CookiesUtil;
import javax.core.common.utils.ToolKit;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tom on 2018/12/27.
 */
public class BaseController {
    /**
     * 获取本地语言环境，优先从session中获取，其次是cookie，然后再从request取
     * @param request
     * @return
     */
    protected String getLocal(HttpServletRequest request){

        Object localObj = request.getSession().getAttribute(SystemConstant.LNG_LOCAL);
        if(null != localObj){
            return localObj.toString();
        }

        String cookie = CookiesUtil.loadCookie(SystemConstant.LNG_LOCAL, request);
        if(!StringUtils.isEmpty(cookie)){
            return cookie;
        }

        String local = "zh_CN";
        local = ToolKit.getString(request, "local",local);
        return local;
    }
}
