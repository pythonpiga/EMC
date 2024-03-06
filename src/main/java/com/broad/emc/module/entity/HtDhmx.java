package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 订货明细表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-25 19:23:03
 */
@Data
@TableName("ht_cpgg")
public class HtDhmx implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * $column.comments
	 */
	private Integer cpdh;
	/**
	 * $column.comments
	 */
	private String cpbh;
	/**
	 * $column.comments
	 */
	private String cpgs;
	/**
	 * $column.comments
	 */
	private String htlb;
	/**
	 * $column.comments
	 */
	private Integer htSno;
	/**
	 * $column.comments
	 */
	private Date dpredate;
	/**
	 * $column.comments
	 */
	private BigDecimal sl;
	/**
	 * $column.comments
	 */
	private BigDecimal slBcsd;
	/**
	 * $column.comments
	 */
	private BigDecimal dj;
	/**
	 * $column.comments
	 */
	private BigDecimal jsj;
	/**
	 * $column.comments
	 */
	private BigDecimal je;
	/**
	 * $column.comments
	 */
	private BigDecimal jsje;
	/**
	 * $column.comments
	 */
	private String bz;
	/**
	 * $column.comments
	 */
	private BigDecimal hl;
	/**
	 * $column.comments
	 */
	private BigDecimal whje;
	/**
	 * $column.comments
	 */
	private String zjmc;
	/**
	 * $column.comments
	 */
	private String memo;
	/**
	 * $column.comments
	 */
	private BigDecimal sjzk;
	/**
	 * $column.comments
	 */
	private BigDecimal sjzkje;
	/**
	 * $column.comments
	 */
	private BigDecimal jd;
	/**
	 * $column.comments
	 */
	private BigDecimal bjj;
	/**
	 * $column.comments
	 */
	private String tcbz;
	/**
	 * $column.comments
	 */
	private BigDecimal tcbl;
	/**
	 * $column.comments
	 */
	private BigDecimal ywytcje;
	/**
	 * $column.comments
	 */
	private String bsyjbz;
	/**
	 * $column.comments
	 */
	private String bcqjlbz;
	/**
	 * $column.comments
	 */
	private String fhsxm;
	/**
	 * $column.comments
	 */
	private String scorderbz;
	/**
	 * $column.comments
	 */
	private String ufsocode;
	/**
	 * $column.comments
	 */
	private Integer ufsomid;
	/**
	 * $column.comments
	 */
	private Integer ufsodautoid;
	/**
	 * $column.comments
	 */
	private String hxytbz;
	/**
	 * $column.comments
	 */
	private String hxtsbz;
	/**
	 * $column.comments
	 */
	private Integer hxtsnd;
	/**
	 * $column.comments
	 */
	private Integer hxtsyf;
	/**
	 * $column.comments
	 */
	private String selected;
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
	private String drbz;
	/**
	 * $column.comments
	 */
	private String htbh;
	/**
	 * $column.comments
	 */
	private String bz2;

}
