package com.own.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.own.common.pojo.ItemCat;
import com.own.service.mapper.ItemCatMapper;
import com.own.service.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wangpl
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * 使用map的方式完成
     *
     * @param type
     * @return Map<parentId, List < ItemCat></>>
     */
    @Override
    public List<ItemCat> findItemCatList(Integer type) {
        //getMap
        //1.查询所有的数据
        //2.封装Map集合数据
        //3. 存储依据 如果key存在?获取子级之后add
        //           如果key不存在？key存储，同时将自己当成第一个元素保存

        //type ==1 map.get(0)
        //type ==2 根据一级查二级
        Map<Integer, List<ItemCat>> map = getMap();
        if (type == 1) return map.get(0);
        if (type == 2) return findTwoItemCatList(map);
        return findThreeItemCatList(map);
    }

    @Override
    public void updateStatus(ItemCat itemCat) {
        itemCatMapper.updateById(itemCat);
    }

    @Override
    @Transactional
    public void saveItemCat(ItemCat itemCat) {
        itemCat.setStatus(true);
        itemCatMapper.insert(itemCat);
    }

    @Override
    public void deleteItemCat(ItemCat itemCat) {
        if (itemCat.getLevel() == 3) {
            itemCatMapper.deleteById(itemCat.getId());
        }
        if (itemCat.getLevel() == 2) {
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", itemCat.getId());
            itemCatMapper.delete(queryWrapper);
            itemCatMapper.deleteById(itemCat.getId());
        }
        if (itemCat.getLevel() == 1) {
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", itemCat.getId());
            List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);
            System.err.println(Arrays.toString(itemCatList.toArray()));
            for (ItemCat itemCat1 : itemCatList) {
                QueryWrapper<ItemCat> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("parent_id", itemCat1.getId());
                itemCatMapper.delete(queryWrapper1);
                itemCatMapper.deleteById(itemCat1.getId());
            }
            itemCatMapper.deleteById(itemCat.getId());
        }
    }

    @Override
    public void updateItemCat(ItemCat itemCat) {
        itemCatMapper.updateById(itemCat);
    }

    public List<ItemCat> findThreeItemCatList(Map<Integer, List<ItemCat>> map) {
        List<ItemCat> oneList = findTwoItemCatList(map);
        for (ItemCat itemCat : oneList) {
            if (itemCat.getChildren() != null) {
                for (ItemCat itemCat2 : itemCat.getChildren()) {
                    List<ItemCat> threeList = map.get((itemCat2.getId()));
                    itemCat2.setChildren(threeList);
                }
            }
        }
        return oneList;
    }

    public List<ItemCat> findTwoItemCatList(Map<Integer, List<ItemCat>> map) {
        //1.获取一级商品分类
        List<ItemCat> oneList = map.get(0);
        //2.根据一级查2级
        for (ItemCat itemCat : oneList) {
            List<ItemCat> twoList = map.get(itemCat.getId());
            itemCat.setChildren(twoList);
        }
        return oneList;
    }

    public Map<Integer, List<ItemCat>> getMap() {
        Map<Integer, List<ItemCat>> map = new HashMap<>();
        //1.查询所有的数据
        List<ItemCat> itemCatList =
                itemCatMapper.selectList(null);
        for (ItemCat itemCat : itemCatList) {
            int parentId = itemCat.getParentId();
            //判断map中是否有父级
            if (map.containsKey(parentId)) {
                //有父级 将自己添加到子级中
                map.get(parentId).add(itemCat);
            } else {
                List<ItemCat> list = new ArrayList<>();
                list.add(itemCat);
                map.put(parentId, list);
            }
        }
        return map;
    }


    /**
     * 实现3级商品分类的嵌套
     * 思考：1.for循环 2.递归 3.尽可能降低数据库查询次数
     * 4.左外连接
     * @param type
     * @return
     */
//    @Override
//    public List<ItemCat> findItemCatList(Integer type) {
//        if(type ==1){//只查询一级商品分类 parent_id 0
//            QueryWrapper<ItemCat> queryWrapper=new QueryWrapper<>();
//            queryWrapper.eq("parent_id", 0);
//            List<ItemCat> oneList=itemCatMapper.selectList(queryWrapper);
//            return oneList;
//        }
//        if(type==2){//查询1级和2级分类信息
//            QueryWrapper<ItemCat> queryWrapper=new QueryWrapper<>();
//            queryWrapper.eq("parent_id", 0);
//            List<ItemCat> oneList=itemCatMapper.selectList(queryWrapper);
//            //根据一级查询二级信息
//            for(ItemCat oneItemCat:oneList){
//                QueryWrapper<ItemCat> queryWrapper2=new QueryWrapper<>();
//                queryWrapper2.eq("parent_id", oneItemCat.getId());
//                List<ItemCat> twoList=itemCatMapper.selectList(queryWrapper2);
//                oneItemCat.setChildren(twoList);
//            }
//            return oneList;
//        }
//        return null;
//    }
}
