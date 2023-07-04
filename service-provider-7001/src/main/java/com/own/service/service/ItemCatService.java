package com.own.service.service;

import com.own.common.pojo.ItemCat;

import java.util.List;

/**
 * @author wjl
 * 2023/1/12 11:51
 */
public interface ItemCatService {
    List<ItemCat> findItemCatList(Integer level);

    void updateStatus(ItemCat itemCat);

    void saveItemCat(ItemCat itemCat);

    void deleteItemCat(ItemCat itemCat);

    void updateItemCat(ItemCat itemCat);
}
