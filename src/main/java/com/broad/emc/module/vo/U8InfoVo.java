package com.broad.emc.module.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * U8数据变更对象
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2024-05-09 08:38:40
 */
@Data
public class U8InfoVo  {
    private String xmdh;
    
    private String htsno;
    
    private String htmc;
    
    private String yyxmbh;

    /**
     * 拆分后新时间
     */
    private String nyNew;

    private String ny;

    /**
     * 调整后临时时间
     */
    private String time;


    private BigDecimal sr;

  


    private BigDecimal cb;
    
    private BigDecimal fy;
   
    private BigDecimal dk;

    /**
     * 拆分后数据
     */
    private BigDecimal srNew;
    /**
     * 拆分后数据
     */
    private BigDecimal cbNew;
    /**
     * 拆分后数据
     */
    private BigDecimal fyNew;
    /**
     * 拆分后数据
     */
    private BigDecimal dkNew;
    
    private String parentNy;
   
    private String opTime;

    /**
     * 自定义调整后的操作时间
     */
    private String opTimeNew;

    /**
     * 自定义拆分标志
     */
    private String flag;
    private String srFlag;
    private String cbFlag;
    private String fyFlag;
    private String dkFlag;

    /**
     * 自定义查询类别：收入成本费用到款
     */
    private String type;


}
