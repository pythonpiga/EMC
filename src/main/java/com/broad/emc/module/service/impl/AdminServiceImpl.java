package com.broad.emc.module.service.impl;

import com.broad.emc.module.dao.AdminDao;
import com.broad.emc.module.entity.Admin;
import com.broad.emc.module.service.AdminService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    public void createAdmin(Admin admin) {
       // adminDao.save(admin);
    }
    
    public Admin findAdminByAccount(String account) {
        return adminDao.findByAccount(account);
    }

    public int updatePassword(String password, String updateTime, String account) {
        return adminDao.updatePassword(password, updateTime, account);
    }
}
