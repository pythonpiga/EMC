package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.Rsjx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ${comments}
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-12-26 14:52:42
 */
public interface RsjxDao extends BaseMapper<Rsjx> {

    @Select( "  select * from ding_rs_jx where dkxh=#{account}  " )
    Rsjx selectOneByDKXH(String account);

}
