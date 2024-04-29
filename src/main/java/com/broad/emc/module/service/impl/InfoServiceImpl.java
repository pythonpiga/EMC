package com.broad.emc.module.service.impl;

import com.broad.emc.module.dao.*;
import com.broad.emc.module.entity.*;
import com.broad.emc.module.service.InfoService;
import com.broad.emc.module.vo.CustomerVo;
import com.broad.emc.module.vo.HtywyVo;
import com.broad.emc.module.vo.RsbmVo;
import com.broad.emc.module.vo.YwyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    
    
    @Resource
    private GsbmDao gsbmDao;
    @Resource
    private SwsDao swsDao;
    @Resource
    private YwyDao ywyDao;
    @Resource
    private XslbDao saletypeDao;
    @Resource
    private JsdwDao jsdwDao;
    @Resource
    private HtlbDao htlbDao;
    @Resource
    private CplxDao cplxDao;
    @Resource
    private XmlbDao cpxmDao;
    @Resource
    private CblbDao cblbDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private RsbmDao rsbmDao;
    @Resource
    private RsjxDao rsjxDao;
    
    
    public List<Gsbm> queryGsbm() {
        return gsbmDao.selectList(null);
    }
    
    public List<Sws> querySws() {
        return swsDao.selectList(null);
    }

    public Sws getSwsId(String swsmc) {
        return swsDao.selectSwsIdBySwsmc(swsmc);
    }
    
    public List<YwyVo> queryYwy(String id) {
        return ywyDao.selectYwyBySwsId(id);
    }
    public String queryYwyId(String xm) {
        return ywyDao.selectYwyIdByXm(xm);
    }

    public HtywyVo getSwsByKhjl(String khjl) {
        return ywyDao.selectSwsByKhjl(khjl);
    }

    public List<Xslb> querySaletype() {
        return saletypeDao.selectList(null);
    }
    
    public List<Jsdw> queryJsdw() {
        return jsdwDao.selectList(null);
    }

    public List<Htlb> queryHtlb() {
        return htlbDao.selectList(null);
    }

    public List<Cplx> queryCplx() {
        return cplxDao.selectList(null);
    }

    public List<Sws> queryYybm() {
        return swsDao.selectYybm();
    }

    public List<Xmlb> queryCpxm() {
        return cpxmDao.selectList(null);
    }

    public List<Cblb> queryCblb() {
        return cblbDao.selectList(null);
    }

    public List<CustomerVo> getCustomer(CustomerVo customerVo) {
        return customerDao.queryList(customerVo);
    }
    public RsbmVo getZgs(String strId){
        return  rsbmDao.getZgsByStrid(strId);
    }

    public List<RsbmVo> getFgs(String strUpid){
        return  rsbmDao.getFgsByStrupid( strUpid);
    }

    public Rsjx findUser(String account){
        return  rsjxDao.selectOneByDKXH( account);
    }

    public String findUserAuth(String account){
        return  rsjxDao.selectUserAuthByDKXH( account);
    }
    





}
