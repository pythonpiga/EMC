package com.broad.emc.module.service;


import com.broad.emc.module.entity.*;
import com.broad.emc.module.vo.*;

import java.util.List;

/**
 * 
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-16 19:38:02
 */
public interface ContractManageService  {

    /**
     * 合同基本信息录入
     * @return
     */
    int addContract(HtxxVo htvo);

    /**
     * 合同录入-订货明细录入
     * @param htDhmx
     * @return
     */
    int addDhmx(HtDhmx htDhmx);

    /**
     *  录入订货明细详细数据(管理时)
     * @param htDhmxVo
     * @return
     */
    int addDhmxDetail(HtDhmxVo htDhmxVo);

    /**
     * 合同录入-机组编号录入
     * @param jzxx
     * @return
     */
    int addJzbh(HtJzbh jzxx);

    /**
     * 根据合同信息对象,查询合同信息
     * @param htxxVo
     * @return
     */
    List<HtxxVo> getContractList(HtxxVo htxxVo);

    /**
     * 查询全部合同信息慢sql,过滤掉ding_rs-_jx的ywy_id查找xm
     * @param htxxVo
     * @return
     */
    List<HtxxVo> getContractList1(HtxxVo htxxVo);

    /**
     * 根据合同编号获取合同信息
     * @param Htbh
     * @return
     */
    Htxx getContractByHtbh(String Htbh);

    
    /**
     * 根据合同序号获取订货明细
     * @param htsno
     * @return
     */
    List<HtDhmxVo> getDhmxList(String htsno);

    /**
     * 根据产品类型获取内部折扣比例
     * @param cplxid
     * @return
     */
    Double queryNbzkblByCplxid(String cplxid);

    /**
     * 根据合同序号获取成本
     * @param htsno
     * @return
     */
    List<HtCb> getCbList(String htsno);

    /**
     * 根据合同序号获取费用
     * @param htsno
     * @return
     */
    List<HtFyVo> getFyList(String htsno);

    
    /**
     * 根据合同序号获取f付款规定
     * @param htsno
     * @return
     */
    List<HtFkgd> getFkgdList(String htsno);

    /**
     * 根据合同序号修改合同基本内容
     * @param 
     * @return
     */
    int updateContractByHtsno(HtxxVo htxxVo);

    /**
     * 根据合同序号获取金额情况
     * @param htsno
     * @return
     */
    HtJeqkVo getJeqkList(String htsno);

    /**
     * 根据合同序号修改合同金额情况
     * @param
     * @return
     */
    int updateJeqkByHtsno(HtJeqkVo htJeqkVo);

    /**
     * 根据产品编号修改合同订货明细
     * @param
     * @return
     */
    int updateDhmxByCpbh(HtDhmxVo htDhmxVo);

    /**
     * 根据产品代号删除合同订货明细
     * @param
     * @return
     */
    int delDhmxByCpdh(HtDhmxVo htDhmxVo);

    /**
     * 根据合同序号删除合同
     * @param htxxVo
     * @return
     */
    //int deleteContractByHtsno(HtxxVo htxxVo);

    /**
     * 根据合同序号htsno新增付款规定
     * @param htFkgd
     * @return
     */
    int addFkgd(HtFkgd htFkgd);

    /**
     * 根据付款序号修改付款规定
     * @param htFkgd
     * @return
     */
    int updateFkgd(HtFkgd htFkgd);

    /**
     * 根据付款序号删除合同付款规定
     * @param htFkgd
     * @return
     */
    int delFkgd(HtFkgd htFkgd);

    /**
     * 根据合同序号获取机组信息
     * @param htsno
     * @return
     */
    List<HtJzxx> getJzxxList(String htsno);

    /**
     * 根据合同序号htsno新增机组编号信息
     * @param htJzxx
     * @return
     */
    int addJzxx(HtJzxx htJzxx);

    /**
     * 根据合同序号、录入时间修改机组信息
     * @param htJzxx
     * @return
     */
    int updateJzxx(HtJzxx htJzxx);

    /**
     * 根据合同序号、录入时间删除机组信息
     * @param htJzxx
     * @return
     */
    int delJzxx(HtJzxx htJzxx);

    /**
     * 根据合同序号htsno新增合同成本
     * @param htcb
     * @return
     */
    int addCb(HtCb htcb);

    /**
     * 根据合同序号、录入时间删除成本
     * @param htcb
     * @return
     */
    int delCb(HtCb htcb);

    /**
     * 根据合同序号、录入时间修改成本
     * @param htcb
     * @return
     */
    int updateCb(HtCb htcb);
    
    /**
     * 根据合同序号htsno新增费用
     * @param htFyVo
     * @return
     */
    int addFy(HtFyVo htFyVo);

    /**
     * 根据项目序号id删除费用
     * @param htFyVo
     * @return
     */
    int delFy(HtFyVo htFyVo);

    /**
     * 根据项目序号id修改费用
     * @param htFyVo
     * @return
     */
    int updateFy(HtFyVo htFyVo);

    /**
     * 根据合同序号获取年服/水处理/节能改造
     * @param htsno
     * @return
     */
    List<HtNf> getNfList(String htsno);

    /**
     * 新增年服、水处理、技改
     * @param htNf
     * @return
     */
    int addNf(HtNf htNf);

    /**
     * 根据服务序号fwSno修改年服、水处理、技改
     * @param htNf
     * @return
     */
    int updateNf(HtNf htNf);

    /**
     * 根据服务序号fwsno删除年服、水处理、技改
     * @param htNf
     * @return
     */
    int delNf(HtNf htNf);
    
    /**
     * 根据合同序号htsno获取合同收款
     * @param htsno
     * @return
     */
    List<HtSkVo> getHtskList(String htsno, String time);
    

    /**
     * 新增合同收款
     * @param htSkVo
     * @return
     */
    int addHtSk(HtSkVo htSkVo);


    /**
     * 根据收款序号skxh修改合同收款
     * @param htSkVo
     * @return
     */
    int updateHtSk(HtSkVo htSkVo);

    /**
     * 根据收款序号skxh删除合同收款
     * @param htSkVo
     * @return
     */
    int delHtSk(HtSkVo htSkVo);

    /**
     * 根据htsno删除合同内容
     * @param htsno
     * @return
     */
    int deleteContract(String htsno);
    
    List<Htxx> getHtmcList();


    /**
     * 收款数据拆分记录保存
     * @param u8InfoVo
     * @return
     */
    void addSkCfData(U8InfoVo u8InfoVo);

    /**
     * 收款数据拆分调整
     * @param u8InfoVo
     * @return
     */
    void updateSkCfData(U8InfoVo u8InfoVo);

    /**
     * 收款根据项目代号日期查询已拆分数据
     * @param
     * @return
     */
    List<U8Info> getSkSplitData(String yyxmbh,String rq);

    /**
     * 收款根据运营项目编号日期查询拆分后新数据
     * @param
     * @return
     */
    List<U8InfoVo> getU8InfoList(String yyxmbh,String ny,String type);
    
    /**
     * 收款根据运营项目编号和日期查询拆分历史
     * @param
     * @return
     */
    List<U8InfoVo> getSplitHistoryData(String yyxmbh,String ny,String nyNew,String type);

    /**
     * 核对收入数据是否已拆分
     * @param u8InfoVo
     * @return
     */
    List<U8InfoVo> CheckSplitData(U8InfoVo u8InfoVo);

    /**
     * 获取U8项目代号
     * @param 
     * @return
     */
    List<U8InfoVo> getU8DataList(String xmdh,String ny);

    /**
     * 获取U8客户编号信息
     * @param
     * @return
     */
    List<U8KhbhVo> getU8KhbhList(U8KhbhVo u8KhbhVo);

    /**
     * 添加U8客户编号信息
     * @param
     * @return
     */
    int saveU8Khbh(U8KhbhVo u8KhbhVo);

    /**
     * 更新U8客户编号信息
     * @param
     * @return
     */
    int updateU8Khbh(U8KhbhVo u8KhbhVo);

    /**
     * 删除U8客户编号信息
     * @param
     * @return
     */
    int delU8Khbh(U8KhbhVo u8KhbhVo);


    /**
     * 核算模型数据统计
     * 获取指定年份的U8数据（去除不达标数据）
     * @param
     * @return
     */
    U8InfoVo getU8ListByTime(String htsno,String yyxmbh,String year,String startTime,String endTime,String type);

    /**
     * 核算模型数据统计
     * 获取历年累计到款数据（去除未达标年份）
     * @param
     * @return
     */
    U8InfoVo getU8DkList(String htsno,String yyxmbh,String year,String startTime,String endTime);

    /**
     * 核算模型数据统计
     * 获取拆分后到款最后实际到款时间（算入分摊期数）
     * @param
     * @return
     */
    U8InfoVo getU8DkSjsj(String htsno,String yyxmbh,String year);



}

