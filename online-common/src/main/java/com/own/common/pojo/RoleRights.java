package com.own.common.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@TableName("role_rights")
@Data
@Accessors(chain = true)
public class RoleRights {
    private Integer roleId;
    private Integer rightsId;
}
