package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * U8数据变更表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2024-04-29 08:38:40
 */
@Data
@TableName("u8_info_new")
public class U8Info implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 项目代号
     */
    @TableId(type = IdType.AUTO)
    private String xmdh;
    /**
     * 合同名称
     */
    private String htmc;
    /**
     * $column.comments
     */
    private String ny;
    /**
     * $column.comments
     */
    private BigDecimal sr;
    /**
     * $column.comments
     */
    private BigDecimal cb;
    /**
     * $column.comments
     */
    private BigDecimal fy;
    /**
     * $column.comments
     */
    private BigDecimal dk;
    /**
     * $column.comments
     */
    private String parentNy;
    /**
     * $column.comments
     */
    private String opTime;

}
