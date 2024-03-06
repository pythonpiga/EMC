package com.broad.emc.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品信息对象
 */
@Data
public class CpxxVo {
    
    private String nd;
    private String cpbm;
    private String cpyjbm;
    private String cpmc;
    private String cpgg;
    private String dw;
    private String cplx;
    private String xmbh;
    private String esj;
    private String fydcp;
    private String bz;
    private BigDecimal bzbj;
    private String tcbl;
    private String gxzc;
    private String shr;
    private String shsj;
    private String lrr;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lrsj;
    

}
