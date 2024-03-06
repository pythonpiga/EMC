package com.broad.emc.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用友客户对象
 */
@Data
public class CustomerVo {

     private String  khbm;
     private String  khmc;
     private String  khjc;
     private String  khflbm;
     @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
     private String  fzrq;
     private String  address;
     private String  yb;
     private String  nsrdjh;
     private String  khh;
     private String  account;
     private String  fr;
     private String  lxr;
     private String  phone;
     private String  fax;
    

}
