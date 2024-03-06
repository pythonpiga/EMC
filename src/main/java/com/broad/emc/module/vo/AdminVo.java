package com.broad.emc.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class AdminVo {
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 类型
     */
    private String type;

    /**
     * 新密码
     */
    private String newPassword;

    


}
