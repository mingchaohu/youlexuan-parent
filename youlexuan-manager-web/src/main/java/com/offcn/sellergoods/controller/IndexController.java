package com.offcn.sellergoods.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
登录控制层
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/getUsername")
    public Map getUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("username", username);
        return map;
    }

}
