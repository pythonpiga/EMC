package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 人事部门表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-12-12 15:13:54
 */
@Data
@TableName("rs_bm")
public class Rsbm implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * $column.comments
     */
    private String bmmc;
    /**
     * $column.comments
     */
    @TableId(type = IdType.AUTO)
    private String bmid;
    /**
     * $column.comments
     */
    private String sbbz;
    /**
     * $column.comments
     */
    private String group;
    /**
     * $column.comments
     */
    private Integer idXh;
    /**
     * $column.comments
     */
    private String pClassify;
    /**
     * $column.comments
     */
    private BigDecimal areaSub;
    /**
     * $column.comments
     */
    private BigDecimal yjxs;
    /**
     * $column.comments
     */
    private String rqdm;
    /**
     * $column.comments
     */
    private String eBmmc;
    /**
     * $column.comments
     */
    private String email;
    /**
     * $column.comments
     */
    private String sort;
    /**
     * $column.comments
     */
    private String strId;
    /**
     * $column.comments
     */
    private String strLevel;
    /**
     * $column.comments
     */
    private String strUpid;
    /**
     * $column.comments
     */
    private Integer strSort;
    /**
     * $column.comments
     */
    private String superid;
    /**
     * $column.comments
     */
    private String cSfzx;
    /**
     * $column.comments
     */
    private String sfzx;
    /**
     * $column.comments
     */
    private Integer vcBmjb;
    /**
     * $column.comments
     */
    private String bmxzjb;
    /**
     * $column.comments
     */
    private String bmManage;
    /**
     * $column.comments
     */
    private String manageId;
    /**
     * $column.comments
     */
    private String vcManage;
    /**
     * $column.comments
     */
    private Date createDate;
    /**
     * $column.comments
     */
    private Date detroyDate;
    /**
     * $column.comments
     */
    private Integer num;
    /**
     * $column.comments
     */
    private String sfmj;
    /**
     * $column.comments
     */
    private String ufBmid;
    /**
     * $column.comments
     */
    private String ufBmmc;
    /**
     * $column.comments
     */
    private String ufFylb;
    /**
     * $column.comments
     */
    private BigDecimal ufFlfjs;
    /**
     * $column.comments
     */
    private String ufBmmc1;
    /**
     * $column.comments
     */
    private Integer dingBm;
    /**
     * $column.comments
     */
    private String kqxxId;
    /**
     * $column.comments
     */
    private String sqsjId;
    /**
     * $column.comments
     */
    private Integer dingBmjb;
    /**
     * $column.comments
     */
    private String dept;
    /**
     * $column.comments
     */
    private String strIdOa;
    /**
     * $column.comments
     */
    private String strUpidOa;
    /**
     * $column.comments
     */
    private String bmmcOa;
    /**
     * $column.comments
     */
    private String flbz;
    /**
     * $column.comments
     */
    private Date dtLog;

}
