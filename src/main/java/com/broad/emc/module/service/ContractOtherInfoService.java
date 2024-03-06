package com.broad.emc.module.service;




import com.broad.emc.module.vo.HtHteVo;
import com.broad.emc.module.vo.HtHtetzxxVo;
import com.broad.emc.module.vo.HtywyVo;

import java.util.List;

/**
 * 
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-16 19:38:02
 */
public interface ContractOtherInfoService {

    /**
     * 根据合同序号htsno获取年合同信息
     * @param htsno
     * @return
     */
    List<HtHteVo> getAnnualContractList(String htsno);

    /**
     * 根据年合同额id获取修改年合同额历史数据
     * @param id
     * @return
     */
    List<HtHtetzxxVo> getHistoryContract(String id);

    /**
     * 录入年合同额信息
     * @param htHteVo
     * @return
     */
    int inputAnnualContract(HtHteVo htHteVo);

    /**
     * 根据合同额序号id删除合同额
     * @param htHteVo
     * @return
     */
    int delAnnualContract(HtHteVo htHteVo);

    /**
     * 根据合同序号htson和合同额序号id修改合同额
     * @param htHteVo
     * @return
     */
    int updateAnnualContract(HtHteVo htHteVo);

    /**
     * 新增修改合同额记录
     * @param htHtetzxxVo
     * @return
     */
    int addNhtRecord(HtHtetzxxVo htHtetzxxVo);


    /**
     * 根据业务员对象查询对应的客户经理信息
     * @param htywyVo
     * @return
     */
    List<HtywyVo> getYwyList(HtywyVo htywyVo);

    /**
     * 添加客户经理
     * @param htywyVo
     * @return
     */
    int addYwy(HtywyVo htywyVo);

    /**
     * 修改客户经理信息
     * @param htywyVo
     * @return
     */
    int updateYwy(HtywyVo htywyVo);

    /**
     * 删除客户经理信息
     * @param htywyVo
     * @return
     */
    int delYwy(HtywyVo htywyVo);

   

    


}

