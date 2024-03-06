package com.broad.emc.module.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 业务员编码
 * @author tkh
 * @date 2023-09-15
 */
@Data
@TableName(value = "ht_ywy_bm")
public class Ywybm implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String ywyId;

    private Integer swsId;

    private String zzbz;

    private Integer xmbId;

    private String gsbz;

    private Date qsrq;

    private Date jzrq;


}


