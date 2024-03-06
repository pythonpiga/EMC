package com.broad.emc.module.service;

import com.broad.emc.module.entity.Admin;

public interface AdminService {

    /**
     * 创建管理员
     *
     * @param admin
     */
    void createAdmin(Admin admin);

    /**
     * 根据账号查询管理员
     *
     * @param account
     * @return
     */
    Admin findAdminByAccount(String account);

    int updatePassword(String password, String updateTime, String account);

}
