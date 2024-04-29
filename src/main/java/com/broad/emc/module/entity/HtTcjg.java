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
 * 提成奖励计算结果表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-11-01 10:18:59
 */
@Data
@TableName("ht_tcjg")
public class HtTcjg implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 合同提成计算奖励结果表
	 */
	@TableId(type = IdType.AUTO)
	private String htSno;
	/**
	 * $column.comments
	 */
	private String year;
	/**
	 * $column.comments
	 */
	private String jd;
	/**
	 * 当期收入
	 */
	private BigDecimal sr;
	/**
	 * 当期成本
	 */
	private BigDecimal cb;
	/**
	 * 当期设备分摊
	 */
	private BigDecimal sbft;
	/**
	 * 当期费用
	 */
	private BigDecimal fy;
	/**
	 * 年服
	 */
	private BigDecimal nf;
	/**
	 * 当期到款
	 */
	private BigDecimal dk;
	/**
	 * 定金
	 */
	private BigDecimal dj;
	/**
	 * 赠送产品
	 */
	private BigDecimal zscp;
	/**
	 * 当期水电气抵扣额
	 */
	private BigDecimal sdq;
	/**
	 * 净利润率
	 */
	private BigDecimal jlrl;
	/**
	 * 提成人员
	 */
	private String tcry;
	/**
	 * 提成比例
	 */
	private BigDecimal tcbl;
	/**
	 * 当期提成奖励
	 */
	private BigDecimal tcje;
	/**
	 * 提成人员类型
	 */
	private String  rylx;
	/**
	 * 前5年剩余30%应发放的提成奖励
	 */
	private BigDecimal tcje5s;
	
	private String wdbnf;
	
	private String time;

	private String opTime;
	
	private String type;
	
	private String htmc;
	
	private String htbh;



}
