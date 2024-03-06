package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.Admin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author tkh
 * @date 2023-09-16
 */
public interface AdminDao extends BaseMapper<Admin> {

    @Select(" select * from ding_emc_admin where account=#{account} ")
    Admin findByAccount(String account);


    @Update(  "update ding_emc_admin set password = #{password},update_time = #{updateTime} where account = #{account} ")
    int updatePassword(String password, String updateTime, String account);
}
