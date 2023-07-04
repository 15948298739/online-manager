package com.own.service.controller;

import com.own.common.pojo.Item;
import com.own.common.vo.ItemVO;
import com.own.common.vo.PageResult;
import com.own.common.vo.SysResult;
import com.own.service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author wjl
 * 2023/1/17 10:09
 */
@RestController
@RequestMapping("item")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("getItemList")
    public SysResult getItemList(PageResult pageResult) {
        pageResult = itemService.getItemList(pageResult);
        return SysResult.success(pageResult);
    }

    @PutMapping("updateItemStatus")
    public SysResult updateItemStatus(@RequestBody Item item) {
//        System.out.println(item);
        itemService.updateItemStatus(item);
        return SysResult.success();
    }

    @DeleteMapping("deleteItemById")
    public SysResult deleteItemById(Integer id) {
        itemService.deleteItemById(id);
        return SysResult.success();
    }

    @PostMapping("updateItem")
    public SysResult updateItem(@RequestBody Item item) {
//        System.out.println(item);
        itemService.updateItem(item);
        return SysResult.success();
    }

    @PostMapping("saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO) {
        System.err.println(itemVO.getItem());
        itemService.saveItem(itemVO.getItem());
        return SysResult.success();
    }
}
