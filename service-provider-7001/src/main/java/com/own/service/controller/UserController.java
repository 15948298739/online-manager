package com.own.service.controller;

import com.own.common.pojo.User;
import com.own.common.vo.PageResult;
import com.own.common.vo.SysResult;
import com.own.service.service.UserService;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public SysResult login(@RequestBody User user) {

        String token = userService.findUserByUP(user);
        if (StringUtils.hasLength(token)) {
            return SysResult.success(token);
        } else {
            return SysResult.fail();
        }
    }

    @GetMapping("/list")
    public SysResult findUsersByPage(PageResult pageResult) {
        return SysResult.success(userService.findUsersByPage(pageResult));
    }

    @PostMapping("/updateStatus")
    public SysResult updateStatus(@RequestBody User user) {
        int row = userService.updateStatus(user);
        return SysResult.success(row);
    }

    @PostMapping("/addUser")
    public SysResult addUser(@RequestBody User user) {
        int row = userService.addUser(user);
        return SysResult.success(row);
    }

    @PutMapping("/updateUser")
    public SysResult updateUser(@RequestBody User user) {
        int row = userService.updateUser(user);
        return SysResult.success(row);
    }

    @DeleteMapping("/{id}")
    public SysResult deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return SysResult.success();
    }
}
