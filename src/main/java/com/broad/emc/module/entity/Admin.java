package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;


/**
 * 管理员信息表
 */
 
@Data
@TableName("ding_emc_admin")
public class Admin {
    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.AUTO)
    private Long id;

    private String account;

    private String password;

    private String createTime;

    private String updateTime;

    private String type;
    
}
