package com.own.service.controller;

import com.own.common.pojo.ItemCatParam;
import com.own.common.vo.SysResult;
import com.own.service.service.ItemCatParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wjl
 * 2023/1/17 11:08
 */
@RestController
@RequestMapping("itemCatParam")
@CrossOrigin
public class ItemCatParamController {
    @Autowired
    private ItemCatParamService itemCatParamService;

    @GetMapping("findItemCatParamListByType")
    public SysResult findItemCatParamListByType(ItemCatParam itemCatParam) {
        List<ItemCatParam> list = itemCatParamService.findItemCatParamListByType(itemCatParam);
        return SysResult.success(list);
    }
    @PutMapping("updateItemCatParam")
    public SysResult updateItemCatParam(@RequestBody ItemCatParam itemCatParam){
        itemCatParamService.updateItemCatParam(itemCatParam);
        return SysResult.success();
    }
    @DeleteMapping("deleteItemCatParamById")
    public SysResult deleteItemCatParamById(Integer paramId){
//        System.out.println(paramId);
        itemCatParamService.deleteItemCatParamById(paramId);
        return SysResult.success();
    }
    @RequestMapping("addItemCatParam")
    public SysResult addItemCatParam(@RequestBody ItemCatParam itemCatParam){
//        System.out.println(itemCatParam);
        itemCatParamService.addItemCatParam(itemCatParam);
        return SysResult.success();
    }
}
