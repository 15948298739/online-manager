package com.own.service.service;

import com.own.common.pojo.ItemCatParam;

import java.util.List;

/**
 * @author wjl
 * 2023/1/17 11:08
 */
public interface ItemCatParamService {
    List<ItemCatParam> findItemCatParamListByType(ItemCatParam itemCatParam);

    void updateItemCatParam(ItemCatParam itemCatParam);

    void deleteItemCatParamById(Integer paramId);

    void addItemCatParam(ItemCatParam itemCatParam);
}
