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
 * 成本表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-27 16:23:50
 */
@Data
@TableName("ht_emccb")
public class HtCb implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 成本表-主键
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
	private Integer cwnd;
	/**
	 * $column.comments
	 */
	private Integer iperiod;
	/**
	 * $column.comments
	 */
	private String xmbh;
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
	private String cblb;
	/**
	 * $column.comments
	 */
	private String cblbmc;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date cbfsrq;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date cbfsjzrq;
	/**
	 * $column.comments
	 */
	private BigDecimal sl;
	/**
	 * $column.comments
	 */
	private String dw;
	/**
	 * $column.comments
	 */
	private BigDecimal dj;
	/**
	 * $column.comments
	 */
	private BigDecimal cbje;
	/**
	 * $column.comments
	 */
	private BigDecimal fpsl;
	/**
	 * $column.comments
	 */
	private BigDecimal sj;
	/**
	 * $column.comments
	 */
	private String dkdjbz;
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
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date pzrq;
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
	private String zy;
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
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date shsj;
	/**
	 * $column.comments
	 */
	private String selected;
	/**
	 * $column.comments
	 */
	private String dkdjbzOld;
	/**
	 * $column.comments
	 */
	private String fjbz;
	/**
	 * $column.comments
	 */
	private String cimportbz;
	/**
	 * $column.comments
	 */
	private Date importdate;

}
