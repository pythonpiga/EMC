package com.broad.emc.module.service.impl;

import com.broad.emc.module.dao.HtHteDao;
import com.broad.emc.module.service.ContractOtherInfoService;
import com.broad.emc.module.vo.HtHteVo;
import com.broad.emc.module.vo.HtHtetzxxVo;
import com.broad.emc.module.vo.HtywyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ContractOtherInfoServiceImpl implements ContractOtherInfoService {
    
    @Resource
    public HtHteDao htHteDao;
    
    public List<HtHteVo> getAnnualContractList(String htsno){
        return htHteDao.queryAnnualContractByHtsno(htsno);
    };

    public List<HtHtetzxxVo> getHistoryContract(String id){
        return htHteDao.queryHistoryById(id);
    };

    public int inputAnnualContract(HtHteVo htHteVo){
        return htHteDao.addAnnualContract(htHteVo);
    };

    public int delAnnualContract(HtHteVo htHteVo){
        return htHteDao.delAnnualContract(htHteVo);
    };

    public int updateAnnualContract(HtHteVo htHteVo){
        return htHteDao.updateByIdAndHtsno(htHteVo);
    };

    public int addNhtRecord(HtHtetzxxVo htHtetzxxVo){
        return htHteDao.addRecord(htHtetzxxVo);
    };

    public List<HtywyVo> getYwyList(HtywyVo htywyVo){
        return htHteDao.getYwyListByHtywyvo(htywyVo);
    };

    public int addYwy(HtywyVo htywyVo){
        return htHteDao.addYwyByHtywyvo(htywyVo);
    };

    public int updateYwy(HtywyVo htywyVo){
        return htHteDao.updateYwyByHtywyvo(htywyVo);
    };

    public int delYwy(HtywyVo htywyVo){
        return htHteDao.deleteYwyByHtywyvo(htywyVo);
    };
    
    


}
