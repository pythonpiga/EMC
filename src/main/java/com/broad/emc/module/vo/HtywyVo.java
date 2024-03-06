package com.broad.emc.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同提成业务员人员及提成比例对象
 */
@Data
public class HtywyVo {
    
    private String htsno;
    private String swsId;
    private String sws;         // 事务所名称
    private String khjl;        
    private String rylb;
    private String htbl;
    private String tcbl;
    private String htmc;
    
    private String zzlb;        // 在职类别 0离职 1 在职 2 调岗
    private String dkxh;        // 打卡序号
    private String khjlid;      // 客户经理id
    private String zzbz;        // 在职标志
    private String gsbm;        // 归属部门
    private String qsrq;        // 起始日期
    private String jzrq;        // 截止日期
    private String gcs;         // 工程师
    private String xm;          // 姓名




}
