package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 费用表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-27 17:44:46
 */
@Data
@TableName("ht_emccbfy")
public class HtFy implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 费用表
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * $column.comments
	 */
	private Integer nd;
	/**
	 * $column.comments
	 */
	private Integer yf;
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
	private String htmc;
	/**
	 * $column.comments
	 */
	private String sort;
	/**
	 * $column.comments
	 */
	private String xmszlb;
	/**
	 * $column.comments
	 */
	private BigDecimal zcfpTrq;
	/**
	 * $column.comments
	 */
	private BigDecimal zcfpZq;
	/**
	 * $column.comments
	 */
	private BigDecimal zcfpCy;
	/**
	 * $column.comments
	 */
	private BigDecimal zcfpDf;
	/**
	 * $column.comments
	 */
	private BigDecimal zcfpSf;
	/**
	 * $column.comments
	 */
	private BigDecimal tzSbgz;
	/**
	 * $column.comments
	 */
	private BigDecimal srGdfsr;
	/**
	 * $column.comments
	 */
	private BigDecimal srBgsr;
	/**
	 * $column.comments
	 */
	private BigDecimal srJlsr;
	/**
	 * $column.comments
	 */
	private BigDecimal srWsrs;
	/**
	 * $column.comments
	 */
	private BigDecimal srOther;
	/**
	 * $column.comments
	 */
	private BigDecimal costMaterial;
	/**
	 * $column.comments
	 */
	private BigDecimal costTax;
	/**
	 * $column.comments
	 */
	private BigDecimal costOil;
	/**
	 * $column.comments
	 */
	private BigDecimal costWater;
	/**
	 * $column.comments                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	 */
	private BigDecimal costElectric;
	/**
	 * $column.comments
	 */
	private BigDecimal costNaturegas;
	/**
	 * $column.comments
	 */
	private BigDecimal costGas;
	/**
	 * $column.comments
	 */
	private BigDecimal costSteam;
	/**
	 * $column.comments
	 */
	private BigDecimal costBj;
	/**
	 * $column.comments
	 */
	private BigDecimal costSbft;
	/**
	 * $column.comments
	 */
	private BigDecimal costWbgc;
	/**
	 * $column.comments
	 */
	private BigDecimal costOther;
	/**
	 * $column.comments
	 */
	private BigDecimal expZjf;
	/**
	 * $column.comments
	 */
	private BigDecimal expGzf;
	/**
	 * $column.comments
	 */
	private BigDecimal expGz;
	/**
	 * $column.comments
	 */
	private BigDecimal expJj;
	/**
	 * $column.comments
	 */
	private BigDecimal expFlf;
	/**
	 * $column.comments
	 */
	private BigDecimal expWxyj;
	/**
	 * $column.comments
	 */
	private BigDecimal expYwtc;
	/**
	 * $column.comments
	 */
	private BigDecimal expBgf;
	/**
	 * $column.comments
	 */
	private BigDecimal expClf;
	/**
	 * $column.comments
	 */
	private BigDecimal expZdf;
	/**
	 * $column.comments
	 */
	private BigDecimal expFzf;
	/**
	 * $column.comments
	 */
	private BigDecimal expMdqx;
	/**
	 * $column.comments
	 */
	private BigDecimal expBjxh;
	/**
	 * $column.comments
	 */
	private BigDecimal expNygwf;
	/**
	 * $column.comments
	 */
	private BigDecimal expGzc;
	/**
	 * $column.comments
	 */
	private BigDecimal expYsf;
	/**
	 * $column.comments
	 */
	private BigDecimal expLdbhf;
	/**
	 * $column.comments
	 */
	private BigDecimal expJsfwf;
	/**
	 * $column.comments
	 */
	private BigDecimal expXcf;
	/**
	 * $column.comments
	 */
	private BigDecimal expHyf;
	/**
	 * $column.comments
	 */
	private BigDecimal expDzyhp;
	/**
	 * $column.comments
	 */
	private BigDecimal expJgf;
	/**
	 * $column.comments
	 */
	private BigDecimal expZtbfwf;
	/**
	 * $column.comments
	 */
	private BigDecimal expLwf;
	/**
	 * $column.comments
	 */
	private BigDecimal expWxf;
	/**
	 * $column.comments
	 */
	private BigDecimal expLyf;
	/**
	 * $column.comments
	 */
	private BigDecimal expGsglf;
	/**
	 * $column.comments
	 */
	private BigDecimal expNbwl;
	/**
	 * $column.comments
	 */
	private BigDecimal expYqndtz;
	/**
	 * $column.comments
	 */
	private BigDecimal expOther;
	/**
	 * $column.comments
	 */
	private String lrr;
	/**
	 * $column.comments
	 */
	private Date lrsj;
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
	private String xmbh;

}
