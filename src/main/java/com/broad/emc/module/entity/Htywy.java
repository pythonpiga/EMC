package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 合同提成业务员人员及提成比例表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-10-23 11:29:57
 */
@Data
@TableName("ht_htywy")
public class Htywy implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.AUTO)
	private Integer htSno;
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
	private String jtnbhzbz;
	/**
	 * $column.comments
	 */
	private String qdfs;
	/**
	 * $column.comments
	 */
	private String hzrylb;
	/**
	 * $column.comments
	 */
	private BigDecimal htbl;
	/**
	 * $column.comments
	 */
	private BigDecimal tcbl;
	/**
	 * $column.comments
	 */
	private BigDecimal yyhtbl;
	/**
	 * $column.comments
	 */
	private BigDecimal yytcbl;
	/**
	 * $column.comments
	 */
	private BigDecimal cqjlbl;
	/**
	 * $column.comments
	 */
	private BigDecimal zhjfbl;
	/**
	 * $column.comments
	 */
	private String lzbz;
	/**
	 * $column.comments
	 */
	private BigDecimal lzqhtfkbl;
	/**
	 * $column.comments
	 */
	private BigDecimal lzqhtktcbl;
	/**
	 * $column.comments
	 */
	private String ywyIdOld;

}
