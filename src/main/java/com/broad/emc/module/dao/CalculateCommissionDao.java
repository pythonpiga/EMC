package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.HtDb;
import com.broad.emc.module.entity.HtExcelData;
import com.broad.emc.module.entity.HtTcjg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CalculateCommissionDao extends BaseMapper<HtExcelData> {

    HtExcelData selectExcelData(String htsno, String jd);

    List<HtExcelData> getDataByHtsno(String htsno,String year);
    
    int addHtEecelData( HtExcelData htExcelData);

    int updateHtEecelData( HtExcelData htExcelData);
    
    int updateWdbnfByHtsno(HtExcelData htExcelData);

    int addTcjg( HtTcjg htTcjg);
    
    @Insert(" insert into ht_db values ( #{htSno},#{htName},#{year},#{jlrl},#{db}  ) ")
    int addDbInfo(HtDb htDb);
    
    @Select(" select * from ht_db where ht_sno=#{htSno} and year=#{year} ")
    HtDb queryDbInfo(String htSno,String year);

    @Select(" select * from ht_db where ht_sno=#{htSno} and db='0' ")
    List<HtDb> queryUnDbList(String htSno);
    
    @Update(" update  ht_db set jlrl=#{jlrl},db=#{db} where ht_sno=#{htSno} and year=#{year}  ")
    int updateDbInfoByHtsnoAndYear(HtDb htDb);

    HtTcjg selectTcjg( HtTcjg htTcjg);

    List<HtTcjg> queryTcjgList(HtTcjg htTcjg);

    int updateTcjgInfo( HtTcjg htTcjg);
    
    List getWdbnfByHtsnoAndTime(String htsno,String time);

}
