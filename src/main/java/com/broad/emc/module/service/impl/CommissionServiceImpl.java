package com.broad.emc.module.service.impl;

import com.broad.emc.module.dao.CalculateCommissionDao;
import com.broad.emc.module.dao.HtywyDao;
import com.broad.emc.module.entity.HtDb;
import com.broad.emc.module.entity.HtExcelData;
import com.broad.emc.module.entity.HtTcjg;
import com.broad.emc.module.service.CommissionService;
import com.broad.emc.module.vo.HtywyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CommissionServiceImpl implements CommissionService {

    @Resource
    private HtywyDao htywyDao;
    @Resource
    private CalculateCommissionDao calculateCommission;
    
    
    public List<HtywyVo> getTcry(String htsno){
        return htywyDao.selectInfoList(htsno);
    }
    
    public int addTcry(HtywyVo htywyVo){
        return htywyDao.insertTcry(htywyVo);
    }

    public int updateTcry(HtywyVo htywyVo){
        return htywyDao.updateTcryByHtsno(htywyVo);
    }

    public int delTcry(HtywyVo htywyVo){
        return htywyDao.delTcryByHtsno(htywyVo);
    }

    public HtExcelData getExcelData(String htsno, String jd){
        return calculateCommission.selectExcelData(htsno,jd);
    }

    public List<HtExcelData> getHtExcelData(String htsno,String year){
        return calculateCommission.getDataByHtsno(htsno,year);
    }

    public int addHtEecel(HtExcelData htExcelData){
        return calculateCommission.addHtEecelData(htExcelData);
    }

    public int updateHtEecel(HtExcelData htExcelData){
        return calculateCommission.updateHtEecelData(htExcelData);
    }

    public int updateWdbnf(HtExcelData htExcelData){
        return calculateCommission.updateWdbnfByHtsno(htExcelData);
    }

    public int saveTcjg(HtTcjg htTcjg){
        return calculateCommission.addTcjg(htTcjg);
    }

    public int saveDbInfo(HtDb htDb){
        return calculateCommission.addDbInfo(htDb);
    }

    public HtDb getDbInfo(String htSno,String year){
        return calculateCommission.queryDbInfo(htSno,year);
    }

    public List<HtDb> getUnDbList(String htSno){
        return calculateCommission.queryUnDbList(htSno);
    }

    public int updateDbInfo(HtDb htDb){
        return calculateCommission.updateDbInfoByHtsnoAndYear(htDb);
    }

    public HtTcjg getTcjgInfo(HtTcjg htTcjg){
        return calculateCommission.selectTcjg(htTcjg);
    }

    public List<HtTcjg> getTcjg(HtTcjg htTcjg){
        return calculateCommission.queryTcjgList(htTcjg);
    }

    public int updateTcjg(HtTcjg htTcjg){
        return calculateCommission.updateTcjgInfo(htTcjg);
    }
    
    public List getWdbnf(String htsno, String time){
        return calculateCommission.getWdbnfByHtsnoAndTime(htsno,time);

    }


//    public BigDecimal getU8Info(String lx, String starttime, String endtime){
//        return calculateCommission.updateTcjgInfo(htTcjg);
//    }


}
