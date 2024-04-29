package com.broad.emc.module.vo;

import lombok.Data;

@Data
public class CommonVo {
    private String htSno;
    private String htmc;
    /**
     * 提成奖励类型
     */
    private String type;
    /**
     * 提成奖励年份
     */
    private String year;
    /**
     * 提成奖励季度
     */
    private String jd;

}
