package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 合同历年净利润率达标信息表
 */
 
@Data
@TableName("ding_db")
public class HtDb {
    private static final long serialVersionUID = 1L;
    
    
    private String htSno;

    private String htName;

    private String year;

    private BigDecimal jlrl;

    private String db;
    
}
