package com.broad.emc.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同金额情况对象
 */
@Data
public class HtJeqkVo {
    private String htsno;
    private String zjts;					//主机台数
    private String ry;						//溶液
    private String zjbzj;				//主机标准价
    private String zjje ;								//主机金额
    private String yggce;			//预估工程额
    private String zjnbzk;		//主机内部折扣
    private String tsf;									//调试费
    private String htgce;								//合同工程额
    private String azf;									//安装费
    private String sjf;									//设计费
    private String wbgce;				//外包工程额
    private String gclr;									//工程利润
    private String jncj;									//节能差价
    private String dzf;					//吊装费
    private String qtf;					//其他费
    private String djztc; 			//大机组提成
    private String zjyf;				//主机运费
    private String tzyf;									//塔组运费
    private String htzje;								//合同总金额
    private String  qtewf;			//其他额外费
    private String zjhte	;								//主机合同额
    private String  qtcpe;  				//其他产品额
    private String nyfwf;							//能源服务费
    private String fzjhte;							//非主机合同额
    private String  qtnbzk ;		//其他内部折扣
    private String  nftze;		//能服调整额
    private String  fydcp;				//非远大产品
    private String  fjje;										//辅机金额
    private String sbfte;  			//设备分摊额
    private String  htbzj; 			//合同标准价
    private String qtbzj;    		//其他标准价
    private String jngze;				//节能改造额
    private String jgje ;									//技改金额
    private String fwxye ;									//服务协议额
    private String  tcbl;										//提成比例
    private String  htbtje;									//合同不提金额
    private String  wkbl;										//尾款比例
    private String  zgbl; 				//主管比例
    private String  xsjlzg;             //享受奖励主管
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date  zgtcjzrq;								//主管提成截止日期
    private String  wbbz;										//外币币种
    private String  fphl; 									//发票汇率
    private String  htwbze;  		//合同外币总额
    

}
