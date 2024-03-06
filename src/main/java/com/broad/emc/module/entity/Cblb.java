package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 成本类别表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-10-16 10:20:47
 */
@Data
@TableName("ht_cblb")
public class Cblb implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成本类别
     */
    @TableId(type = IdType.AUTO)
    private String cblb;
    /**
     * $column.comments
     */
    private String cblbmc;
    /**
     * $column.comments
     */
    private BigDecimal sl;
    /**
     * $column.comments
     */
    private String yt;
    /**
     * $column.comments
     */
    private String ytid;

}
