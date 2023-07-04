package com.own.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wjl
 * 2023/1/9 11:00
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private String query;
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private Object rows;
}
