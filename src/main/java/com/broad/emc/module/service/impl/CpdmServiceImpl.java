package com.broad.emc.module.service.impl;

import com.broad.emc.module.entity.Cpxx;
import com.broad.emc.module.dao.CpxxDao;
import com.broad.emc.module.service.CpdmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CpdmServiceImpl  implements CpdmService {
    
    @Resource
    private CpxxDao cpdmDao;


    public List<Cpxx> queryAll(String nd, String cplx) {    
        return  cpdmDao.selectByNdAndCplx(nd,cplx);
    }

    public int insertCpxx(Cpxx cpdm){
        return cpdmDao.insert(cpdm);
    }

    public int delCpxxByCpbm(String cpbm){
        return cpdmDao.deleteByCpbm(cpbm);
    }

    public int updateCpxxByCpbm(Cpxx cpdm){
        return cpdmDao.updateByCpbm(cpdm);
    }
    
    public int queryNum(String cplx) {
        return  cpdmDao.selectNum(cplx);
    }


}
