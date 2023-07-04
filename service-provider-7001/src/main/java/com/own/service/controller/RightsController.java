package com.own.service.controller;

import com.own.common.pojo.Rights;
import com.own.common.vo.SysResult;
import com.own.service.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wjl
 * 2023/1/8 16:55
 */
@RestController
@CrossOrigin
@RequestMapping("/rights")
public class RightsController {
    @Autowired
    private RightsService rightsService;

@GetMapping("getRightsList")
    public SysResult getRightsList(){
    List<Rights> rightsList = rightsService.findList();
    return SysResult.success(rightsList);
}
}
