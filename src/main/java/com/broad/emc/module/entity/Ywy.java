package com.broad.emc.module.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * 业务员
 * @author tkh
 * @date 2023-09-15
 */
@Data
@TableName(value = "ht_ywy")
public class Ywy implements Serializable {

    private static final long serialVersionUID = 1L;


    private String ywyId;

    private String ywyXm;

    private String zzsz;

    private String sfgcs;

    private Date zzsj;

    private BigDecimal mxzb;

    private BigDecimal ljhte;

    private BigDecimal dqjzye;

    private BigDecimal jssjzs;

    private BigDecimal lk;

    private BigDecimal jl;

    private BigDecimal ktcje;

    private BigDecimal wclhttc;

    private String beizhu;

    private BigDecimal qetc;

    private BigDecimal jdtc;

    private String kskhnd;

    private String kskhyf;

    private String eMail;

    private Date cdswsrq;

    private String pyzt;

    private BigDecimal yswkjz;

    private BigDecimal htwkjz;

    private String gsbz;

    private String id;

    private String dkxh;

    private Date pyrq;

    private String drbz;

    private Integer swsId;



}



