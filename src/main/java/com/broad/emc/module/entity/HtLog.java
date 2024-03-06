package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2024-03-02 10:34:53
 */
@Data
@TableName("ht_log")
public class HtLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * $column.comments
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 操作用户名
     */
    private String userName;
    /**
     * 操作说明
     */
    private String operateContent;
    /**
     * 方法名
     */
    private String method;
    /**
     * 操作方法
     */
    private String controller;
    /**
     * 操作时间
     */
    private Date operateTime;
    /**
     * 方法参数
     */
    private String parameters;
    /**
     * 返回值
     */
    private String returnValue;

}
