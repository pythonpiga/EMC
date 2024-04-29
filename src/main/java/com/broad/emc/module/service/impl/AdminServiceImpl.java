package com.broad.emc.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broad.emc.module.dao.AdminDao;
import com.broad.emc.module.entity.Admin;
import com.broad.emc.module.service.AdminService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    
    public PageInfo getPage(Integer pageNum,Integer pageSize){
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        PageInfo page=new PageInfo(adminDao.selectList(null));
        return  page;
    }
}
