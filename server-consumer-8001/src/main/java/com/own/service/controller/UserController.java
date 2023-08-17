package com.own.service.controller;

import com.own.common.pojo.User;
import com.own.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wjl
 * 2023/7/19 15:54
 */
@RestController
public class UserController {
    private static final String URL = "http://localhost:7001/";
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public SysResult login(@RequestBody User user) {
        return restTemplate.postForObject(URL + "user/login", user, SysResult.class);
    }
}
