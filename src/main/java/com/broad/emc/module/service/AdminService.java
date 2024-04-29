package com.broad.emc.module.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broad.emc.module.entity.Admin;
import com.github.pagehelper.PageInfo;

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

    /**
     * 分页测试
     * @param pageNum
     * @param pageTotal
     * @return
     */
    PageInfo getPage(Integer pageNum , Integer pageTotal);

}
