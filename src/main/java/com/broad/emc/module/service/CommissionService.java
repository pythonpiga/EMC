package com.broad.emc.module.service;


import com.broad.emc.module.entity.HtDb;
import com.broad.emc.module.entity.HtExcelData;
import com.broad.emc.module.entity.HtTcjg;
import com.broad.emc.module.vo.CommonVo;
import com.broad.emc.module.vo.HtywyVo;

import java.util.List;

/**
 * 
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-10-23 19:38:02
 */
public interface CommissionService {

    /**
     * 根据合同序号htsno获取提成人员及比例信息
     * @param htsno
     * @return
     */
    List<HtywyVo> getTcry(String htsno);

    /**
     * 根据合同序号htsno获取提成人员变更历史记录
     * @param htsno
     * @return
     */
    List<HtywyVo> getTcryHistoryList(String htsno);

    /**
     * 新增提成人员及比例信息
     * @param htywyVo
     * @return
     */
    int addTcry(HtywyVo htywyVo);

    /**
     * 根据合同序号htsno修改提成人员及比例信息
     * @param htywyVo
     * @return
     */
    int updateTcry(HtywyVo htywyVo);

    /**
     * 根据合同序号htsno删除提成人员及比例信息
     * @param htywyVo
     * @return
     */
    int delTcry(HtywyVo htywyVo);

    /**
     * 根据合同序号htsno跟季度jd查询导入的数据
     */
    HtExcelData getExcelData(String htsno, String jd);

    /**
     * 根据合同序号 年份 查找导入的合同excel数据
     * @param htsno
     * @return
     */
    List<HtExcelData> getHtExcelData(String htsno,String year);


    /**
     * 新增手工导入的合同数据
     * @param htExcelData
     * @return
     */
    int addHtEecel(HtExcelData htExcelData);

    /**
     * 手工导入的合同数据存在相同记录修改
     * @param htExcelData
     * @return
     */
    int updateHtEecel(HtExcelData htExcelData);
    

    /**
     * 提成奖励计算结果保存到数据库
     */
    int saveTcjg(HtTcjg htTcjg);

    /**
     * 更新提成奖励计算结果到数据库
     */
    int updateTcjg(HtTcjg htTcjg);

    /**
     * 记录合同历年净利润率达标信息
     * @param htDb
     * @return
     */
    int saveDbInfo(HtDb htDb);
    
    /**
     * 修改合同历年净利润率达标信息
     * @param htDb
     * @return
     */
    int updateDbInfo(HtDb htDb);

    /**
     * 查询合同历年净利润率达标信息
     * @param 
     * @return
     */
    HtDb getDbInfo(String htSno,String year,String db);

    /**
     * 查询合同历年净利润率未达标年份集合
     * @param
     * @return
     */
    List<HtDb> getUnDbList(String htSno);

    /**
     * 查询提成信息
     */
    HtTcjg getTcjgInfo(HtTcjg htTcjg);

    /**
     * 查询提成信息
     */
    List<HtTcjg> getTcjg(HtTcjg htTcjg);

    /**
     * 查询提成信息
     */
    List<HtTcjg> getTcjgList(HtTcjg htTcjg);




    /**
     * 根据htsno跟年份 获取当前时间之前未达标年份
     * @param htsno
     * @param time
     * @return
     */
    List getWdbnf(String htsno, String time);

    
//    BigDecimal getU8Info(String lx, String starttime, String endtime);
    
}

