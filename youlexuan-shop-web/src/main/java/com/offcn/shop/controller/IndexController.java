package com.offcn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
登录控制层
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/getUsernameAndLastLogintime")
    public Map getUsernameAndLastLogintime() {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("sellerId", sellerId);
        Object lastLogintime = redisTemplate.boundHashOps("shopWebLastLogintime").get(sellerId);
        if (lastLogintime == null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            String time = sdf.format(date);
            map.put("lastLogintime", time);
            redisTemplate.boundHashOps("shopWebLastLogintime").put(sellerId, time);
        } else {
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
            String time = sf.format(date);
            redisTemplate.boundHashOps("shopWebLastLogintime").put(sellerId, time);
            map.put("lastLogintime", lastLogintime);
        }
        return map;
    }

}

