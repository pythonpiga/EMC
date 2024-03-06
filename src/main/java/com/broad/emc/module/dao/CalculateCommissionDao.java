package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.HtExcelData;
import com.broad.emc.module.entity.HtTcjg;

import java.util.List;

public interface CalculateCommissionDao extends BaseMapper<HtExcelData> {

    HtExcelData selectExcelData(String htsno, String jd);

    List<HtExcelData> getDataByHtsno(String htsno,String year);
    
    int addHtEecelData( HtExcelData htExcelData);

    int updateHtEecelData( HtExcelData htExcelData);
    
    int updateWdbnfByHtsno(HtExcelData htExcelData);

    int addTcjg( HtTcjg htTcjg);

    HtTcjg selectTcjg( HtTcjg htTcjg);

    List<HtTcjg> queryTcjgList(HtTcjg htTcjg);

    int updateTcjgInfo( HtTcjg htTcjg);
    
    List getWdbnfByHtsnoAndTime(String htsno,String time);

}
