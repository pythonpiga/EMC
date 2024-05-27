package com.broad.emc.module.service.impl;

import com.broad.emc.module.dao.HtxxDao;
import com.broad.emc.module.entity.*;
import com.broad.emc.module.service.ContractManageService;
import com.broad.emc.module.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class ContractManageServiceImpl implements ContractManageService {

    @Resource
    private HtxxDao htxxDao;
    
    public int addContract(HtxxVo htvo) {
       return htxxDao.insertContract(htvo);
    }

    public int addDhmx(HtDhmx htDhmx) {
        return htxxDao.insertDhmx(htDhmx);
    }

    public int addDhmxDetail(HtDhmxVo htDhmxVo) {
        return htxxDao.insertDhmxDetail(htDhmxVo);
    }
    
    public int addJzbh(HtJzbh jzxx) {
        return htxxDao.insertJzbh(jzxx);
    }

    public List<HtxxVo> getContractList(HtxxVo htxxVo) {
        return htxxDao.queryContract(htxxVo);
    }
    public List<HtxxVo> getContractList1(HtxxVo htxxVo) {
        return htxxDao.queryContract1(htxxVo);
    }

    public Htxx getContractByHtbh(String  htbh) {
        return htxxDao.queryContractByHtbh(htbh);
    }

    public List<HtSkVo> getHtskList(String htsno,String time) {
        return htxxDao.queryHtskByHtsno(htsno,time);
    }

    
    
    public int addHtSk(HtSkVo htSkVo) {
        return htxxDao.insertHtSk(htSkVo);
    }

    public int updateHtSk(HtSkVo htSkVo) {
        return htxxDao.updateHtSkById(htSkVo);
    }

    public int delHtSk(HtSkVo htSkVo) {
        return htxxDao.deleteHtSkById(htSkVo);
    }
    
    public int deleteContract(String htsno){
        return htxxDao.deleteContractByHtsno(htsno);
    }
    
    public List<HtDhmxVo> getDhmxList(String htsno) {
        return htxxDao.queryDhmxByHtsno(htsno);
    }

    public Double queryNbzkblByCplxid(String cplxid) {
        return htxxDao.selectNbzkblByCplxid(cplxid);
    }

    public List<HtCb> getCbList(String htsno) {
        return htxxDao.queryCbByHtsno(htsno);
    }

    public List<HtFyVo> getFyList(String htsno) {
        return htxxDao.queryFyByHtsno(htsno);
    }
    
    public List<HtFkgd> getFkgdList(String htsno) {
        return htxxDao.queryFkgdByHtsno(htsno);
    }

    public int updateContractByHtsno(HtxxVo htxxVo) {
        return htxxDao.updateContractByHtsno(htxxVo);
    }

    public HtJeqkVo getJeqkList(String htsno) {
        return htxxDao.queryJeqkByHtsno(htsno);
    }

    public int updateJeqkByHtsno(HtJeqkVo htJeqkVo) {
        return htxxDao.updateJeqkByHtsno(htJeqkVo);
    }

    public int updateDhmxByCpbh(HtDhmxVo htDhmxVo) {
        return htxxDao.updateDhmxByCpbh(htDhmxVo);
    }

    public int delDhmxByCpdh(HtDhmxVo htDhmxVo) {
        return htxxDao.deleteByCpdh(htDhmxVo);
    }

    public int addFkgd(HtFkgd htFkgd) {
        return htxxDao.addFkgdByHtsno(htFkgd);
    }

    public int updateFkgd(HtFkgd htFkgd) {
        return htxxDao.updateFkgdByGdno(htFkgd);
    }

    public int delFkgd(HtFkgd htFkgd) {
        return htxxDao.delFkgdByGdno(htFkgd);
    }
    
//    public int deleteContractByHtsno(HtxxVo htxxVo) {
//        return htxxDao.deleteContractByHtsno(htxxVo);
//    }

    public List<HtJzxx> getJzxxList(String htsno) {
        return htxxDao.selectJzxxByHtsno(htsno);
    }

    public int addJzxx(HtJzxx htJzxx) {
        return htxxDao.addJzxxByHtsno(htJzxx);
    }
    
    public int updateJzxx(HtJzxx htJzxx) {
        return htxxDao.updateByHtsnoAndLrsj(htJzxx);
    }

    public int delJzxx(HtJzxx htJzxx) {
        return htxxDao.delByHtsnoAndLrsj(htJzxx);
    }

    public int addCb(HtCb htcb) {
        return htxxDao.addCbByHtsno(htcb);
    }

    public int delCb(HtCb htcb) {
        return htxxDao.delCbByHtsno(htcb);
    }

    public int updateCb(HtCb htcb) {
        return htxxDao.updateCbByHtsno(htcb);
    }

    public int addFy(HtFyVo htFyVo) {
        return htxxDao.addFyByHtsno(htFyVo);
    }

    public int delFy(HtFyVo htFyVo) {
        return htxxDao.delFyById(htFyVo);
    }

    public int updateFy(HtFyVo htFyVo) {
        return htxxDao.updateFyById(htFyVo);
    }

    public List<HtNf> getNfList(String htsno) {
        return htxxDao.queryNfByHtsno(htsno);
    }

    public int addNf(HtNf htNf) {
        return htxxDao.insertNf(htNf);
    }

    public int updateNf(HtNf htNf) {
        return htxxDao.updateNfByFwsno(htNf);
    }

    public int delNf(HtNf htNf) {
        return htxxDao.deleteNfByFwsno(htNf);
    }
    
    public List<Htxx> getHtmcList(){
        return htxxDao.selectHtmcList();
    }


    public void addSkCfData(U8InfoVo u8InfoVo) {
        //数据拆分 更新拆分标志
        htxxDao.updateSkOpflag(u8InfoVo);

        //数据拆分 原始数据 =》 0
        htxxDao.insertSkCf(u8InfoVo);

        //数据统计 拆分后新数据
        htxxDao.insertSkTj(u8InfoVo);
    }

    public void updateSkCfData(U8InfoVo u8InfoVo) {
        htxxDao.updateSkSplitData(u8InfoVo);
        
    }

    public List<U8Info> getSkSplitData(String yyxmbh,String rq) {
        return htxxDao.querySkSplitDataList(yyxmbh,rq);
    }
    
    public List<U8InfoVo> getU8InfoList(String yyxmbh,String ny,String type){
        return htxxDao.queryU8InfoList(yyxmbh,ny,type);
    }

    public List<U8InfoVo> getSplitHistoryData(String yyxmbh,String ny,String nyNew,String type){
        List<U8InfoVo> u8InfoVos=new ArrayList<>();
        if( "sr".equals(type) ){
            u8InfoVos= htxxDao.querySrSplitHistoryData(yyxmbh,ny,nyNew);
        }else if( "cb".equals(type) ){
            u8InfoVos= htxxDao.queryCbSplitHistoryData(yyxmbh,ny,nyNew);
        }else if( "fy".equals(type) ){
            u8InfoVos= htxxDao.queryFySplitHistoryData(yyxmbh,ny,nyNew);
        }else if( "dk".equals(type) ){
            u8InfoVos= htxxDao.queryDkSplitHistoryData(yyxmbh,ny,nyNew);
        }
        return u8InfoVos;
    }

    public List<U8InfoVo> CheckSplitData(U8InfoVo u8InfoVo) {
        return htxxDao.queryIsSplitData(u8InfoVo);

    }

    public List<U8InfoVo> getU8DataList(String xmdh,String ny){
        return htxxDao.queryU8DataList(xmdh,ny);
    }

    public List<U8KhbhVo> getU8KhbhList(U8KhbhVo u8KhbhVo){
        return htxxDao.queryU8KhbhList(u8KhbhVo);
    }

    public int saveU8Khbh(U8KhbhVo u8KhbhVo){
        return htxxDao.addU8Khbh(u8KhbhVo);
    }

    public int updateU8Khbh(U8KhbhVo u8KhbhVo){
        return htxxDao.updateU8KhbhByVo(u8KhbhVo);
    }

    public int delU8Khbh(U8KhbhVo u8KhbhVo){
        return htxxDao.deleteU8KhbhByVo(u8KhbhVo);
    }

    public U8InfoVo getU8ListByTime(String htsno,String yyxmbh,String year,String startTime,String endTime,String type){
        return htxxDao.queryU8ListByTime( htsno,yyxmbh,year,startTime,endTime,type);
    }

    public U8InfoVo getU8DkList(String htsno,String yyxmbh,String year,String startTime,String endTime){
        return htxxDao.queryU8DkList(htsno,yyxmbh,year,startTime,endTime);
    }

    public U8InfoVo getU8DkSjsj(String htsno,String yyxmbh,String year){
        return htxxDao.queryU8DkSjsj(htsno,yyxmbh,year);
    }



}
