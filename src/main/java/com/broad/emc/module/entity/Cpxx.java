package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 产品信息表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-19 10:25:48
 */
@Data
@TableName("ht_cpdm")
public class Cpxx implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.AUTO)
	private Integer cpdh;
	/**
	 * $column.comments
	 */
	private String cpbh;
	/**
	 * $column.comments
	 */
	private String htfylb;
	/**
	 * $column.comments
	 */
	private String cpmc;
	/**
	 * $column.comments
	 */
	private String jx;
	/**
	 * $column.comments
	 */
	private String category;
	/**
	 * $column.comments
	 */
	private String model;
	/**
	 * $column.comments
	 */
	private String cpgg;
	/**
	 * $column.comments
	 */
	private String dw;
	/**
	 * $column.comments
	 */
	private String xmbh;
	/**
	 * $column.comments
	 */
	private String cinvcode;
	/**
	 * $column.comments
	 */
	private String cinvname;
	/**
	 * $column.comments
	 */
	private String cinvstd;
	/**
	 * $column.comments
	 */
	private String cinvccode;
	/**
	 * $column.comments
	 */
	private String ccomunitcode;
	/**
	 * $column.comments
	 */
	private String binvmodel;
	/**
	 * $column.comments
	 */
	private String batomodel;
	/**
	 * $column.comments
	 */
	private String bptomodel;
	/**
	 * $column.comments
	 */
	private String csrpolicy;
	/**
	 * $column.comments
	 */
	private String cppy;
	/**
	 * $column.comments
	 */
	private BigDecimal bzbj;
	/**
	 * $column.comments
	 */
	private String fhdj;
	/**
	 * $column.comments
	 */
	private BigDecimal tcbl;
	/**
	 * $column.comments
	 */
	private BigDecimal fh;
	/**
	 * $column.comments
	 */
	private String cpfl;
	/**
	 * $column.comments
	 */
	private String cpgs;
	/**
	 * $column.comments
	 */
	private Integer cwnd;
	/**
	 * $column.comments
	 */
	private String eszjf;
	/**
	 * $column.comments
	 */
	private String jzbz;
	/**
	 * $column.comments
	 */
	private String fydcpbz;
	/**
	 * $column.comments
	 */
	private String bz;
	/**
	 * $column.comments
	 */
	private String cstopusebz;
	/**
	 * $column.comments
	 */
	private Date dstopuse;
	/**
	 * $column.comments
	 */
	private String lrr;
	/**
	 * $column.comments
	 */

	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date lrsj;
	/**
	 * $column.comments
	 */
	private String shbz;
	/**
	 * $column.comments
	 */

	@TableField(value = "shr",insertStrategy = FieldStrategy.IGNORED)
	private String shr;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date shsj;
	/**
	 * $column.comments
	 */
	private String drbz;

}
