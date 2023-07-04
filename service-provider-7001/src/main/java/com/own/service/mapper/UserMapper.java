package com.own.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.own.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
