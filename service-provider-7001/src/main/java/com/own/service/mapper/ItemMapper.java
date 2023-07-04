package com.own.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.own.common.pojo.Item;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wjl
 * 2023/1/17 10:22
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {
}
