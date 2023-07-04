package com.own.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.own.common.pojo.ItemCatParam;
import com.own.service.mapper.ItemCatParamMapper;
import com.own.service.service.ItemCatParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wjl
 * 2023/1/17 11:08
 */
@Service
public class ItemCatParamServiceImpl implements ItemCatParamService {
    @Autowired
    private ItemCatParamMapper itemCatParamMapper;

    @Override
    public List<ItemCatParam> findItemCatParamListByType(ItemCatParam itemCatParam) {
        QueryWrapper<ItemCatParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("param_type", itemCatParam.getParamType()).eq("item_cat_id", itemCatParam.getItemCatId());
        List<ItemCatParam> itemCatParams = itemCatParamMapper.selectList(queryWrapper);
        return itemCatParams;
    }

    @Override
    public void updateItemCatParam(ItemCatParam itemCatParam) {
        QueryWrapper<ItemCatParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("param_id", itemCatParam.getParamId());
        itemCatParamMapper.update(itemCatParam, queryWrapper);
    }

    @Override
    public void deleteItemCatParamById(Integer paramId) {
        itemCatParamMapper.deleteById(paramId);
    }

    @Override
    public void addItemCatParam(ItemCatParam itemCatParam) {
        itemCatParamMapper.insert(itemCatParam);
    }
}
