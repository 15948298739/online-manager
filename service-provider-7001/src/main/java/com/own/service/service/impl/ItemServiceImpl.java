package com.own.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.own.common.pojo.Item;
import com.own.common.vo.PageResult;
import com.own.service.mapper.ItemMapper;
import com.own.service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author wjl
 * 2023/1/17 10:22
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public PageResult getItemList(PageResult pageResult) {
        IPage<Item> iPage = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper<>();
        Boolean flag = StringUtils.hasLength(pageResult.getQuery());
        queryWrapper.like(flag, "title", pageResult.getQuery());
        iPage = itemMapper.selectPage(iPage, queryWrapper);
        pageResult.setTotal(iPage.getTotal());
        pageResult.setRows(iPage.getRecords());
        return pageResult;
    }

    @Override
    public void updateItemStatus(Item item) {
        itemMapper.updateById(item);
    }

    @Override
    public void deleteItemById(Integer id) {
        itemMapper.deleteById(id);
    }

    @Override
    public void updateItem(Item item) {
        itemMapper.updateById(item);
    }

    @Override
    public void saveItem(Item item) {
        item.setStatus(true);
        itemMapper.insert(item);
    }
}
