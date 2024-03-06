package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目类别信息
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-19 15:18:27
 */
@Data
@TableName("ht_cpxm")
public class Xmlb implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.AUTO)
	private String xmbh;
	/**
	 * $column.comments
	 */
	private String xmmc;
	/**
	 * $column.comments
	 */
	private String lb;
	/**
	 * $column.comments
	 */
	private String sort;

}
