package com.gupaoedu.security.model.vo;

import lombok.Data;

/**
 * Created by Tom on 2018/12/30.
 */
@Data
public class LoginVo {
    private String loginName;
    private String loginPass;
    private String enc;
    private String iframe = "0";
    private String callback = "callback";
    private String jumpto = "/";

}
