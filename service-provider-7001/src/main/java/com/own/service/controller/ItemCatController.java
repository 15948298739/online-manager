package com.own.service.controller;

import com.own.common.pojo.ItemCat;
import com.own.common.vo.SysResult;
import com.own.service.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wjl
 * 2023/1/12 11:50
 */
@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("findItemCatList/{level}")
    public SysResult findItemCatList(@PathVariable Integer level) {
        List<ItemCat> itemCatList = itemCatService.findItemCatList(level);
        return SysResult.success(itemCatList);
    }

    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(ItemCat itemCat) {
        itemCatService.updateStatus(itemCat);
        return SysResult.success();
    }

    @PostMapping("saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat){
        itemCatService.saveItemCat(itemCat);
        return SysResult.success();
    }

    @DeleteMapping("deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat){
        itemCatService.deleteItemCat(itemCat);
        return SysResult.success();
    }

    @PutMapping("updateItemCat")
    public SysResult updateItemCat(@RequestBody ItemCat itemCat){
        itemCatService.updateItemCat(itemCat);
        return SysResult.success();
    }
}
