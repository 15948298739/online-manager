package com.own.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.own.common.pojo.Rights;
import com.own.service.mapper.RightsMapper;
import com.own.service.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wjl
 * 2023/1/8 16:55
 */
@Service
public class RightsServiceImpl implements RightsService {
    @Autowired
    private RightsMapper rightsMapper;

    @Override
    public List<Rights> findList() {
        QueryWrapper<Rights> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        List<Rights> rightsList = rightsMapper.selectList(queryWrapper);
        for (Rights rights : rightsList) {
            queryWrapper.clear();
            queryWrapper.eq("parent_id", rights.getId());
            List<Rights> twoList = rightsMapper.selectList(queryWrapper);
            rights.setChildren(twoList);
        }
        return rightsList;
    }
}
