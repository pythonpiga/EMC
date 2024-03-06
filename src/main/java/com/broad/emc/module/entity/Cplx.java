package com.broad.emc.module.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 产品类型
 * @author tkh
 * @date 2023-09-16
 */
@Data
@TableName(value ="ht_htfyl")
public class Cplx implements Serializable {

    private static final long serialVersionUID = 1L;


    private String htfylb;

    private String htfymc;

    private BigDecimal tsfbl;

    private String zjbz;

    private BigDecimal nbzkbl;

    private String dh;

    private String cpdl;

    private String cpzylb;

    private String saletype;

    private String cstopbz;

    private String csort;


}


