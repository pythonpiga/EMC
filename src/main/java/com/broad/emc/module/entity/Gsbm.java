package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 公司部门
 * @author tkh
 * @date 2023-09-15
 */
@Data
@TableName(value = "ht_gsbm")
public class Gsbm {
    private String gsbm;
    private String gsbz;
    private String gsbmmc;
    private String gsgs;
    private String gsgsdh;
}
