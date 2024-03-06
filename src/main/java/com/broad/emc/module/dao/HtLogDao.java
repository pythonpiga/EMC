package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.HtLog;
import org.apache.ibatis.annotations.Insert;

/**
 * @author tkh
 * @date 2023-09-16
 */
public interface HtLogDao extends BaseMapper<HtLog> {

//   @Insert("insert intp ht_log values( #{log.userName},#{log.operateContent},#{log.method},#{log.controller}," +
//           "#{log.operateTime},#{log.parameters},#{log.returnValue} ) ")
//    int addLogRecord(HtLog log);
}
