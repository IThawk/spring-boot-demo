package com.gupaoedu.security.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gupaoedu.core.mvc.controller.BaseController;
import com.gupaoedu.security.model.entity.Member;
import com.gupaoedu.security.model.vo.LoginVo;
import com.gupaoedu.security.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.core.common.ResultMsg;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/system")
public class SystemController extends BaseController{

    @Autowired private MemberService memberService;

    /**
     * 检查用户是否存在
     * @param request
     * @param loginName
     * @param enc
     * @return
     */
    @RequestMapping(value="/loginNameExists.json")
    public Mono<Object> loginNameExists(HttpServletRequest request,
                                         String loginName,
                                         String enc) {
        String local = super.getLocal(request);
        ResultMsg<?> result = memberService.getByLoginName(local, loginName, enc);
        return Mono.just(result);
    }

    /**
     * 用户的登录
     * @return
     */
    @PostMapping("/login.json")
    public ResponseEntity login(LoginVo vo){
        ResultMsg<?> data = memberService.login(vo.getLoginName(),vo.getLoginPass(),vo.getEnc());
        String json = JSON.toJSONString(data);
        return processForLogin(json,vo.getIframe(),vo.getCallback(),vo.getJumpto());
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout.json")
    public Mono<Object> logout(){
        ResultMsg<?> result = memberService.logout(null);
        return Mono.just(result);
    }

    private ResponseEntity processForLogin(String json, String iframe, String callback, String jumpto){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        if(!(null == jumpto || "".equals(jumpto))) {
            JSONObject obj = JSON.parseObject(json);
            if(obj.getJSONObject("data") != null) {
                obj.getJSONObject("data").put("jumpto", jumpto);
            }
            json = obj.toString();
        }

        if("1".equals(iframe)) {
            StringBuffer returnStr = new StringBuffer();
            returnStr.append("window.parent." + ((callback == null) ? "callback" : callback) + "(" + json + ");");
            returnStr.insert(0, "<script type=\"text/javascript\">").append("</script>");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.TEXT_HTML)
                    .body(returnStr.toString());
        }else{
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(((callback == null) ? json : (callback +"(" + json + ")")));
        }
    }
}
