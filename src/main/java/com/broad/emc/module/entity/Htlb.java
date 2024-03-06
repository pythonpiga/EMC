package com.broad.emc.module.entity;



import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 合同类别
 * @author tkh
 * @date 2023-09-16
 */
@Data
@TableName(value ="ht_htlb")
public class Htlb implements Serializable {

    private static final long serialVersionUID = 1L;


    private String htlb;
    private String htlbmc;


}


