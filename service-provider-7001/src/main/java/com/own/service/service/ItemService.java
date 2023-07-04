package com.own.service.service;

import com.own.common.pojo.Item;
import com.own.common.vo.PageResult;

/**
 * @author wjl
 * 2023/1/17 10:22
 */
public interface ItemService {
    PageResult getItemList(PageResult pageResult);

    void updateItemStatus(Item item);

    void deleteItemById(Integer id);

    void updateItem(Item item);

    void saveItem(Item item);
}
