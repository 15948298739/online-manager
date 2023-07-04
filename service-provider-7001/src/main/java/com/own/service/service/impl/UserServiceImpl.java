package com.own.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.own.common.pojo.User;
import com.own.common.vo.PageResult;
import com.own.service.mapper.UserMapper;
import com.own.service.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public String findUserByUP(User user) {
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        User result = userMapper.selectOne(queryWrapper);
        String token = UUID.randomUUID().toString().replace("-", "");
        return result == null ? null : token;
    }

    @Override
    public PageResult findUsersByPage(PageResult pageResult) {
//        定义分页对象
        IPage<User> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        queryWrapper.like(flag, "username", pageResult.getQuery());
        page = userMapper.selectPage(page, queryWrapper);
        long total = page.getTotal();
        List<User> rows = page.getRecords();
        pageResult.setTotal(total).setRows(rows);
        return pageResult;
    }

    @Override
    public int updateStatus(User user) {
        user.setStatus(user.getStatus());
        return userMapper.updateById(user);
    }

    @Override
    public int addUser(User user) {
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }
}
