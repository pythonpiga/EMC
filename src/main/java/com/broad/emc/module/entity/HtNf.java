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
 * 年服/水处理/节能改造表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-28 09:35:53
 */
@Data
@TableName("ht_emcfwht")
public class HtNf implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	private Integer htSno;
	/**
	 * $column.comments
	 */
	private Integer fwSno;
	/**
	 * $column.comments
	 */
	private Integer yynd;
	/**
	 * $column.comments
	 */
	private String swsMc;
	/**
	 * $column.comments
	 */
	private String dcxmdh;
	/**
	 * $column.comments
	 */
	private Integer swsId;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date shsj;
	/**
	 * $column.comments
	 */
	private String shr;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date qsrq;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date lrsj;
	/**
	 * $column.comments
	 */
	private String lrr;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date jzrq;
	/**
	 * $column.comments
	 */
	private String htbh;
	/**
	 * $column.comments
	 */
	private String gcsXm;
	/**
	 * $column.comments
	 */
	private String gcsId;
	/**
	 * $column.comments
	 */
	private String fwmc;
	/**
	 * $column.comments
	 */
	private BigDecimal fwje;
	/**
	 * $column.comments
	 */
	private String fwbh;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date fk10date;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date dqrq;
	/**
	 * $column.comments
	 */
	private String bz;
	/**
	 * $column.comments
	 */
	private BigDecimal bjbj;

}
