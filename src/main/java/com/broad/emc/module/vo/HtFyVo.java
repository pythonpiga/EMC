package com.broad.emc.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用对象
 */
@Data
public class HtFyVo {
    private String htsno;
    private Integer id;
    private String nd;
    private String yf;
    private String xmszlb;
    private String htmc;
    private BigDecimal gz;
    private BigDecimal flf;
    private BigDecimal gzc;
    private BigDecimal wxyj;
    private BigDecimal zjf;
    private BigDecimal fzf;
    private BigDecimal jj;
    private BigDecimal bgf;
    private BigDecimal clf;
    private BigDecimal zdf;
    private BigDecimal ysf;
    private BigDecimal ldbhf;
    private BigDecimal jsfwf;
    private BigDecimal xcf;
    private BigDecimal hyf;
    private BigDecimal dzyhp;
    private BigDecimal jgf;
    private BigDecimal ztbfwf;
    private BigDecimal lwf;
    private BigDecimal wxf;
    private BigDecimal ywtc;
    private BigDecimal lyf;
    private BigDecimal qt;
    private String lrr;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lrsj;
    private String sh;
    private String shr;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date shsj;
}

