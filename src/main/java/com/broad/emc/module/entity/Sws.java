package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 事务所表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-11-06 17:34:31
 */
@Data
@TableName("ht_sws")
public class Sws implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 事务所表
     */
    @TableId(type = IdType.AUTO)
    private Integer swsId;
    /**
     * $column.comments
     */
    private String swsMc;
    /**
     * $column.comments
     */
    private String swsJc;
    /**
     * $column.comments
     */
    private String bmid;
    /**
     * $column.comments
     */
    private String bmmc;
    /**
     * $column.comments
     */
    private String dqdh;
    /**
     * $column.comments
     */
    private Integer fwbId;
    /**
     * $column.comments
     */
    private String lxr;
    /**
     * $column.comments
     */
    private String lxdh;
    /**
     * $column.comments
     */
    private String cz;
    /**
     * $column.comments
     */
    private Integer zgsDh;
    /**
     * $column.comments
     */
    private String eMail;
    /**
     * $column.comments
     */
    private BigDecimal xsjbl;
    /**
     * $column.comments
     */
    private String dkxh;
    /**
     * $column.comments
     */
    private String dkxh1;
    /**
     * $column.comments
     */
    private String xm1;
    /**
     * $column.comments
     */
    private String dkxh2;
    /**
     * $column.comments
     */
    private String xm2;
    /**
     * $column.comments
     */
    private String dkxh3;
    /**
     * $column.comments
     */
    private String xm3;
    /**
     * $column.comments
     */
    private String dkxh4;
    /**
     * $column.comments
     */
    private String xm4;
    /**
     * $column.comments
     */
    private String zgdkxh1;
    /**
     * $column.comments
     */
    private String zgxm1;
    /**
     * $column.comments
     */
    private String zgdkxh2;
    /**
     * $column.comments
     */
    private String zgxm2;
    /**
     * $column.comments
     */
    private String lxr1;
    /**
     * $column.comments
     */
    private String bmlb;
    /**
     * $column.comments
     */
    private String gsbm;
    /**
     * $column.comments
     */
    private String ufdepcode;
    /**
     * $column.comments
     */
    private String ufdepname;
    /**
     * $column.comments
     */
    private String ufcitemccode;
    /**
     * $column.comments
     */
    private String yjbmmc;

}
