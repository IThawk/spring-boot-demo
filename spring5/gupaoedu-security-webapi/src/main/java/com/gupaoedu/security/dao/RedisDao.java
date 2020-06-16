package com.gupaoedu.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Tom on 2018/12/30.
 */
@Repository
public class RedisDao {
    @Autowired
    StringRedisTemplate template;

    public void put(String key,String value){
        template.opsForValue().set(key,value);
    }

    public String get(String key){
        return template.opsForValue().get(key);
    }

    public boolean hasKey(String key){
        return template.hasKey(key);
    }

    public void putKey(String key){
        template.opsForValue().set(key,"");
    }
}
