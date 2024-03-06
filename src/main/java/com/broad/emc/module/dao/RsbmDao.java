package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.broad.emc.module.entity.Rsbm;
import com.broad.emc.module.vo.RsbmVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RsbmDao extends BaseMapper<Rsbm> {

    @Select( "  select bmmc,bmid,str_id as strId,str_upid as strUpid from rs_bm where str_id=#{strId} and c_sfzx='n'  " )
    RsbmVo getZgsByStrid(String strId);

    @Select( "  select bmmc,bmid,str_id as strId,str_upid as strUpid from rs_bm where str_upid=#{strUpid} and c_sfzx='n' order by str_id asc  " )
    List<RsbmVo> getFgsByStrupid(String strUpid);
    
   
    
}
