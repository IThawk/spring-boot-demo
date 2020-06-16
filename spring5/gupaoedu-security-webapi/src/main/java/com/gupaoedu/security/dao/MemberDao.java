package com.gupaoedu.security.dao;

import com.gupaoedu.security.common.constans.SystemConstant;
import com.gupaoedu.security.model.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2018/12/27.
 */
@Repository
public class MemberDao {

    private Map<String,Member> members = new HashMap<String,Member>();

    public MemberDao(){
        members.put("tom",new Member("tom","123456"));
    }

    public Member selectByLoginName(String loginName) {
        return this.members.get(loginName);
    }
}
