package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机组编号信息表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-25 21:17:27
 */
@Data
@TableName("ht_jzxx")
public class HtJzbh implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * $column.comments
	 */
	private Integer fwSno;
	/**
	 * $column.comments
	 */
	private String fwbh;
	/**
	 * $column.comments
	 */
	private String jx;
	/**
	 * $column.comments
	 */
	private String sjxh;
	/**
	 * $column.comments
	 */
	private Integer gl;
	/**
	 * $column.comments
	 */
	private String ccbh;
	/**
	 * $column.comments
	 */
	private String jzxh;
	/**
	 * $column.comments
	 */
	private Integer sl;
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
	private String htbh;

}
