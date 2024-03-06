package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 年合同额表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-10-09 10:18:28
 */
@Data
@TableName("ht_hte")
public class HtHte implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 年合同额表
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * $column.comments
	 */
	private Integer htSno;
	/**
	 * $column.comments
	 */
	private Integer nd;
	/**
	 * $column.comments
	 */
	private Date qsrq;
	/**
	 * $column.comments
	 */
	private Date jzrq;
	/**
	 * $column.comments
	 */
	private Integer yyys;
	/**
	 * $column.comments
	 */
	private BigDecimal khsbtze;
	/**
	 * $column.comments
	 */
	private BigDecimal htje;
	/**
	 * $column.comments
	 */
	private BigDecimal gdfje;
	/**
	 * $column.comments
	 */
	private BigDecimal sbfte;
	/**
	 * $column.comments
	 */
	private BigDecimal gcwbe;
	/**
	 * $column.comments
	 */
	private BigDecimal jngze;
	/**
	 * $column.comments
	 */
	private BigDecimal jge;
	/**
	 * $column.comments
	 */
	private BigDecimal jnfxe;
	/**
	 * $column.comments
	 */
	private BigDecimal jnfxbl;
	/**
	 * $column.comments
	 */
	private BigDecimal fwxye;
	/**
	 * $column.comments
	 */
	private BigDecimal sbyjzle;
	/**
	 * $column.comments
	 */
	private BigDecimal yyyjzle;
	/**
	 * $column.comments
	 */
	private BigDecimal khdkdje;
	/**
	 * $column.comments
	 */
	private BigDecimal tzje;
	/**
	 * $column.comments
	 */
	private BigDecimal tcje;
	/**
	 * $column.comments
	 */
	private String yyzt;
	/**
	 * $column.comments
	 */
	private Integer jslrnd;
	/**
	 * $column.comments
	 */
	private BigDecimal ndsjyyjlrl;
	/**
	 * $column.comments
	 */
	private Date ndjlrlqsrq;
	/**
	 * $column.comments
	 */
	private Date ndjlrljzrq;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzjlr;
	/**
	 * $column.comments
	 */
	private String jslrndshbz;
	/**
	 * $column.comments
	 */
	private String jslrndshr;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzsr;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzcb;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzfy;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzlr;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzml;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzlrl;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzsr1;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzcb1;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzfy1;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzlr1;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzml1;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzzxhlr;
	/**
	 * $column.comments
	 */
	private BigDecimal ndzxhlrl;
	/**
	 * $column.comments
	 */
	private BigDecimal ndxzxjl;
	/**
	 * $column.comments
	 */
	private String fkfs;
	/**
	 * $column.comments
	 */
	private String fjbz;
	/**
	 * $column.comments
	 */
	private BigDecimal yfbl;
	/**
	 * $column.comments
	 */
	private BigDecimal yfje;
	/**
	 * $column.comments
	 */
	private String ywyId;
	/**
	 * $column.comments
	 */
	private String zgdh;
	/**
	 * $column.comments
	 */
	private BigDecimal zglrtcje;
	/**
	 * $column.comments
	 */
	private String ktcbz;
	/**
	 * $column.comments
	 */
	private String fk10bz;
	/**
	 * $column.comments
	 */
	private Date fk10date;
	/**
	 * $column.comments
	 */
	private Date fk10dateSbyjzl;
	/**
	 * $column.comments
	 */
	private Date fk10dateYyyjzl;
	/**
	 * $column.comments
	 */
	private Date fk10dateSbtz;
	/**
	 * $column.comments
	 */
	private Date fk01date;
	/**
	 * $column.comments
	 */
	private Date fk03date;
	/**
	 * $column.comments
	 */
	private Date fk09date;
	/**
	 * $column.comments
	 */
	private String firstndbz;
	/**
	 * $column.comments
	 */
	private String yjlockbz;
	/**
	 * $column.comments
	 */
	private String bsyjbz;
	/**
	 * $column.comments
	 */
	private Integer yynd;
	/**
	 * $column.comments
	 */
	private BigDecimal tcjeBak;
	/**
	 * $column.comments
	 */
	private String mlkjjbz;
	/**
	 * $column.comments
	 */
	private String sfsnbz;
	/**
	 * $column.comments
	 */
	private String xgr;
	/**
	 * $column.comments
	 */
	private Date xgsj;
	/**
	 * $column.comments
	 */
	private String cstatus;
	/**
	 * $column.comments
	 */
	private String selected;

}
