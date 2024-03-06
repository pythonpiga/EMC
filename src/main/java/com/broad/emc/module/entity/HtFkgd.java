package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 付款规定表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-28 15:44:45
 */
@Data
@TableName("ht_fkgdxm")
public class HtFkgd implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 付款规定表
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
	private String htbh;
	/**
	 * $column.comments
	 */
	private String fwbh;
	/**
	 * $column.comments
	 */
	private Integer rowxh;
	/**
	 * $column.comments
	 */
	private String gdNo;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date fkrq;
	/**
	 * $column.comments
	 */
	private BigDecimal fkje;
	/**
	 * $column.comments
	 */
	private Integer fkjg;
	/**
	 * $column.comments
	 */
	private String bz;
	/**
	 * $column.comments
	 */
	private String fklb;
	/**
	 * $column.comments
	 */
	private String bzdh;
	/**
	 * $column.comments
	 */
	private BigDecimal yhje;
	/**
	 * $column.comments
	 */
	private Date hkrq;
	/**
	 * $column.comments
	 */
	private String fjbz;
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

}
