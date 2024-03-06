package com.broad.emc.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同收款对象
 */
@Data
public class HtSkVo {
    
    private String htsno;
    private String htbh;
    private String skxh;
    private String skdh;
    private String srlb;
    private String nd;
    private String dkrq;
    private String dkje;
    private String dkdjbz;
    private String bhje;
    private String bankaccount;
    private String yykhbm;
    private String yhkhmc;
    private String lrr;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date lrsj;
    private String zg;
    private String khjl;
    private String zgtc;
    private String hxqtewf;
    private String beizhu;


}
