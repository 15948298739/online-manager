package com.own.service.service;

import com.own.common.pojo.User;
import com.own.common.vo.PageResult;

import java.util.List;


public interface UserService {
    List<User> findAll();

    String findUserByUP(User user);

    PageResult findUsersByPage(PageResult pageResult);

    int updateStatus(User user);

    int addUser(User user);

    int updateUser(User user);

    void deleteUser(Integer id);
}
