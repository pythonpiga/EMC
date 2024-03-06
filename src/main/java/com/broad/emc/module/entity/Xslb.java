package com.broad.emc.module.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 销售类别
 * @author tkh
 * @date 2023-09-16
 */
@Data
@TableName(value ="ht_saletype")
public class Xslb implements Serializable {

    private static final long serialVersionUID = 1L;


    private String saletype;

    private String saletypemc;

    private BigDecimal tcbl;


}


