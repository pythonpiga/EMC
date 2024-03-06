package com.broad.emc.module.controller;

import com.broad.emc.module.entity.*;
import com.broad.emc.module.service.ContractManageService;
import com.broad.emc.module.service.ContractOtherInfoService;
import com.broad.emc.module.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 
 * 合同其他信息管理
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-10-16 19:38:02
 */
@Slf4j
@RestController
@RequestMapping("emc/ContractOtherInfo")
public class ContractOtherInfoController {
    
    @Autowired
    private ContractOtherInfoService contractOtherInfoService;

    /**
     *  根据合同序号htsno获取年合同额集合
     * @param htsno
     * @return
     */
    @RequestMapping("/getAnnualContractList")
    public ReturnData getAnnualContractList(@RequestParam String htsno){
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            List<HtHteVo> htHteVoList=contractOtherInfoService.getAnnualContractList(htsno);
            ret.setData(htHteVoList);
            ret.setMsg("查询合同额成功");

        } catch (Exception e) {
            log.error("查询合同额信息失败，异常：{}", e);
            return ReturnData.getExceptionData("查询合合同额信息失败，异常");
        }

        return ret;
    };

    /**
     *  根据年合同额id获取修改年合同额历史数据
     * @param id
     * @return
     */
    @RequestMapping("/getHistoryContract")
    public ReturnData getHistoryContract(@RequestParam String id){
        if (id == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            List<HtHtetzxxVo> htHtetzxxVoList=contractOtherInfoService.getHistoryContract(id);
            ret.setData(htHtetzxxVoList);
            ret.setMsg("查询历史修改合同额记录成功");

        } catch (Exception e) {
            log.error("查询历史修改合同额记录失败，异常：{}", e);
            return ReturnData.getExceptionData("查询历史修改合同额记录失败，异常");
        }

        return ret;
    };

    /**
     *  录入年合同额
     * @param htHteVoList
     * @return
     */
    @RequestMapping("/inputAnnualContract")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData inputAnnualContract(@RequestBody List<HtHteVo> htHteVoList){
        if (htHteVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            for(HtHteVo htHteVo : htHteVoList){
                int result=contractOtherInfoService.inputAnnualContract(htHteVo);
                if(result>0){
                    ret.setMsg("录入年合同额成功");
                }
            }
           
        } catch (Exception e) {
            log.error("录入年合同额失败，异常：{}", e);
            return ReturnData.getExceptionData("录入年合同额失败，异常");
        }

        return ret;
    };

    /**
     *  根据年合同额序号id删除年合同额
     * @param htHteVoList
     * @return
     */
    @RequestMapping("/delAnnualContract")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delAnnualContract(@RequestBody List<HtHteVo> htHteVoList){
        if (htHteVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            for(HtHteVo htHteVo : htHteVoList){
                int result=contractOtherInfoService.delAnnualContract(htHteVo);
                if(result>0){
                    ret.setMsg("成功删除年合同额");
                }
            }

        } catch (Exception e) {
            log.error("删除年合同额失败，异常：{}", e);
            return ReturnData.getExceptionData("删除年合同额失败，异常");
        }

        return ret;
    };

    
    /**
     *  根据年合同额序号id跟合同序号htsno修改年合同额
     * @param htHteVo
     * @return
     */
    @RequestMapping("/updateAnnualContract")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateAnnualContract(@RequestBody HtHteVo htHteVo){
        if (htHteVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
                
                int result=contractOtherInfoService.updateAnnualContract(htHteVo);
                if(result>0 ){
                    ret.setMsg("成功修改年合同额");
                }
            

        } catch (Exception e) {
            log.error("修改年合同额失败，异常：{}", e);
            return ReturnData.getExceptionData("修改年合同额失败，异常");
        }

        return ret;
    };

    /**
     * 修改年合同额增加修改记录
     * @param htHtetzxxVo
     * @return
     */
    @RequestMapping("/addNhtRecord")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addNhtRecord(@RequestBody HtHtetzxxVo htHtetzxxVo){
        if (htHtetzxxVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {

            int result=contractOtherInfoService.addNhtRecord(htHtetzxxVo);
            
            if(result>0 ){
                ret.setMsg("成功添加年合同额修改记录");
            }


        } catch (Exception e) {
            log.error("添加年合同额修改记录失败，异常：{}", e);
            return ReturnData.getExceptionData("添加年合同额修改记录失败，异常");
        }

        return ret;
    };

    /**
     *  根据业务员对象查询对应的客户经理
     * @param htywyVoList
     * @return
     */
    @RequestMapping("/addYwy")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addYwy(@RequestBody List<HtywyVo> htywyVoList){
        if (htywyVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            for(HtywyVo htywyVo : htywyVoList){
                int res=contractOtherInfoService.addYwy(htywyVo);
            }
            ret.setData(htywyVoList);
            ret.setMsg("添加成功");

        } catch (Exception e) {
            log.error("添加失败，异常：{}", e);
            return ReturnData.getExceptionData("添加失败，异常");
        }

        return ret;
    };


    /**
     *  添加客户经理
     * @param htywyVo
     * @return
     */
    @RequestMapping("/getYwyList")
    public ReturnData getYwyList(@RequestBody HtywyVo htywyVo){
        if (htywyVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            List<HtywyVo> htywyVoList=contractOtherInfoService.getYwyList(htywyVo);
            //ht_ywy新增数据后 执行触发器 ht_ywy_bm新增
            ret.setData(htywyVoList);
            ret.setMsg("查询成功");

        } catch (Exception e) {
            log.error("查询失败，异常：{}", e);
            return ReturnData.getExceptionData("查询失败，异常");
        }

        return ret;
    };

    /**
     *  修改客户经理信息
     * @param htywyVoList
     * @return
     */
    @RequestMapping("/updateYwy")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateYwy(@RequestBody List<HtywyVo> htywyVoList){
        if (htywyVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            for(HtywyVo htywyVo:htywyVoList){
                int result=contractOtherInfoService.updateYwy(htywyVo);
            }
            //ht_ywy修改数据后 执行触发器 ht_ywy_bm修改
            ret.setMsg("修改成功");

        } catch (Exception e) {
            log.error("修改失败，异常：{}", e);
            return ReturnData.getExceptionData("修改失败，异常");
        }

        return ret;
    };


    /**
     *  删除客户经理信息
     * @param htywyVoList
     * @return
     */
    @RequestMapping("/delYwy")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delYwy(@RequestBody List<HtywyVo> htywyVoList){
        if (htywyVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            for(HtywyVo htywyVo:htywyVoList){
                int result=contractOtherInfoService.delYwy(htywyVo);
            }
            //ht_ywy修改数据后 执行触发器 ht_ywy_bm修改
            ret.setMsg("删除成功");
        } catch (Exception e) {
            log.error("删除失败，异常：{}", e);
            return ReturnData.getExceptionData("删除失败，异常");
        }

        return ret;
    };


   
    
    
}
