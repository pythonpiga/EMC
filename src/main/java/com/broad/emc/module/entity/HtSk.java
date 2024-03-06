package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 合同收款表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-27 09:22:21
 */
@Data
@TableName("ht_fkb")
public class HtSk implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 合同收款表-主键
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * $column.comments
	 */
	private Integer parentid;
	/**
	 * $column.comments
	 */
	private Integer ufId;
	/**
	 * $column.comments
	 */
	private Integer htSno;
	/**
	 * $column.comments
	 */
	private String htbh;
	/**
	 * $column.comments
	 */
	private String fwbh;
	/**
	 * $column.comments
	 */
	private String payNo;
	/**
	 * $column.comments
	 */
	private String cvouchidUf;
	/**
	 * $column.comments
	 */
	private Integer iperiod;
	/**
	 * $column.comments
	 */
	private String srlb;
	/**
	 * $column.comments
	 */
	private String skxz;
	/**
	 * $column.comments
	 */
	private String dealerbz;
	/**
	 * $column.comments
	 */
	private String isscdk;
	/**
	 * $column.comments
	 */
	private String jscode;
	/**
	 * $column.comments
	 */
	private String jscodename;
	/**
	 * $column.comments
	 */
	private String citemClass;
	/**
	 * $column.comments
	 */
	private String citemcode;
	/**
	 * $column.comments
	 */
	private String citemname;
	/**
	 * $column.comments
	 */
	private String yscode;
	/**
	 * $column.comments
	 */
	private String yscodename;
	/**
	 * $column.comments
	 */
	private String ccuscode;
	/**
	 * $column.comments
	 */
	private String ccusname;
	/**
	 * $column.comments
	 */
	private String ccusabbname;
	/**
	 * $column.comments
	 */
	private String cdepcode;
	/**
	 * $column.comments
	 */
	private String cdepname;
	/**
	 * $column.comments
	 */
	private String cpersondkxh;
	/**
	 * $column.comments
	 */
	private String cnoteno;
	/**
	 * $column.comments
	 */
	private String cdigest;
	/**
	 * $column.comments
	 */
	private Integer bankid;
	/**
	 * $column.comments
	 */
	private String cbankcode;
	/**
	 * $column.comments
	 */
	private String cbank;
	/**
	 * $column.comments
	 */
	private String cbankaccount;
	/**
	 * $column.comments
	 */
	private String cbankaccname;
	/**
	 * $column.comments
	 */
	private String cnatbank;
	/**
	 * $column.comments
	 */
	private String cnatbankaccount;
	/**
	 * $column.comments
	 */
	private String cnatbankaccname;
	/**
	 * $column.comments
	 */
	private BigDecimal dkje;
	/**
	 * $column.comments
	 */
	private BigDecimal bhje;
	/**
	 * $column.comments
	 */
	private Date dkrq;
	/**
	 * $column.comments
	 */
	private BigDecimal hzskje;
	/**
	 * $column.comments
	 */
	private String dkdjbz;
	/**
	 * $column.comments
	 */
	private String hzbz;
	/**
	 * $column.comments
	 */
	private BigDecimal hzbl;
	/**
	 * $column.comments
	 */
	private BigDecimal yfbl;
	/**
	 * $column.comments
	 */
	private BigDecimal hxqtewfje;
	/**
	 * $column.comments
	 */
	private Integer swsId;
	/**
	 * $column.comments
	 */
	private String ywyId;
	/**
	 * $column.comments
	 */
	private String ywyXm;
	/**
	 * $column.comments
	 */
	private BigDecimal tcbl;
	/**
	 * $column.comments
	 */
	private BigDecimal ywyjdtc;
	/**
	 * $column.comments
	 */
	private String ywytcbz;
	/**
	 * $column.comments
	 */
	private String fklrr;
	/**
	 * $column.comments
	 */
	private Date fklrsj;
	/**
	 * $column.comments
	 */
	private String bz;
	/**
	 * $column.comments
	 */
	private BigDecimal whje;
	/**
	 * $column.comments
	 */
	private String ddId;
	/**
	 * $column.comments
	 */
	private BigDecimal hl;
	/**
	 * $column.comments
	 */
	private Integer wkId;
	/**
	 * $column.comments
	 */
	private String fkfs;
	/**
	 * $column.comments
	 */
	private String jsfsUf;
	/**
	 * $column.comments
	 */
	private String pzlb;
	/**
	 * $column.comments
	 */
	private String pzh;
	/**
	 * $column.comments
	 */
	private Date pzrq;
	/**
	 * $column.comments
	 */
	private String fjbz;
	/**
	 * $column.comments
	 */
	private String memo;
	/**
	 * $column.comments
	 */
	private String shbz;
	/**
	 * $column.comments
	 */
	private String shr;
	/**
	 * $column.comments
	 */
	private Date shsj;
	/**
	 * $column.comments
	 */
	private String selected;
	/**
	 * $column.comments
	 */
	private Integer yynd;
	/**
	 * $column.comments
	 */
	private Integer hknd;
	/**
	 * $column.comments
	 */
	private String zgdh;
	/**
	 * $column.comments
	 */
	private BigDecimal zgjdtc;
	/**
	 * $column.comments
	 */
	private String zgtcbz;
	/**
	 * $column.comments
	 */
	private String cstatus;
	/**
	 * $column.comments
	 */
	private String cimportbz;
	/**
	 * $column.comments
	 */
	private String cfdbz;
	/**
	 * $column.comments
	 */
	private String isvalid;
	/**
	 * $column.comments
	 */
	private String sdbz;
	/**
	 * $column.comments
	 */
	private String czdfdbz;

}
