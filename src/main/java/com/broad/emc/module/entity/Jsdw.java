package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 结算单位
 * @author tkh
 * @date 2023-09-16
 */
@Data
@TableName(value ="ht_tcjsdw")
public class Jsdw implements Serializable {

    private static final long serialVersionUID = 1L;


    private String tcjsdwdh;

    private String tcjsdwmc;


}


