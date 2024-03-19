package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 导入的excel合同数据表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-11-04 10:50:47
 */
@Data
@TableName("ht_exceldata")
public class HtExcelData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.AUTO)
	private String htSno;
	
	private String htmc;
	/**
	 * $column.comments
	 */
	private String qdrq;
	/**
	 * $column.comments
	 */
	private String yyrq;
	/**
	 * 设备分摊(月)
	 */
	private BigDecimal sbft;
	/**
	 * 定金
	 */
	private BigDecimal dj;
	/**
	 * 年服
	 */
	private BigDecimal nf;
	/**
	 * 赠送产品
	 */
	private BigDecimal zscp;
	/**
	 * 截止2021.12.31定金
	 */
	private BigDecimal dj2021;
	/**
	 * 截止2021.12.31赠送产品
	 */
	private BigDecimal zscp2021;
	/**
	 * 截止2021.12.31年服
	 */
	private BigDecimal nf2021;
	/**
	 * 运营款收齐标志
	 */
	private String yyksqbz;

	private String year;

	private String jd;

	/**
	 * 未达标年份(不计入累计计奖基数)
	 */
	private String  wdbnf;

	/**
	 * 水电气抵扣额
	 */
	private BigDecimal sdqdke;
	


}
