package com.own.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wjl
 * 2023/1/8 13:34
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {
    private Integer status;
    private String msg;
    private Object data;

    public static SysResult fail() {
        return new SysResult(201, "服务器响应失败", null);
    }

    public static SysResult success() {
        return new SysResult(200, "服务器响应成功", null);
    }

    public static SysResult success(Object data) {
        return new SysResult(200, "服务器响应成功", data);
    }

    public static SysResult success(String msg, Object data) {
        return new SysResult(200, msg, data);
    }
}
