package com.broad.emc.module.service.impl;

import com.broad.emc.module.dao.HtxxDao;
import com.broad.emc.module.entity.*;
import com.broad.emc.module.service.ContractManageService;
import com.broad.emc.module.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

 

}
