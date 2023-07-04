package com.own.common.vo;
import com.own.common.pojo.Item;
import com.own.common.pojo.ItemDesc;
import com.own.common.pojo.ItemParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemVO {  //该对象封装商品所有的参数信息
    private Item item;
    private ItemDesc itemDesc;
    private ItemParam itemParam;
}
