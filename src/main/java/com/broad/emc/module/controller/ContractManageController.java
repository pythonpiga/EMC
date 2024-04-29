package com.broad.emc.module.controller;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.broad.emc.module.entity.*;
import com.broad.emc.module.entity.HtFkgd;
import com.broad.emc.module.service.AdminService;
import com.broad.emc.module.vo.ReturnData;
import com.broad.emc.module.service.ContractManageService;
import com.broad.emc.module.vo.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;





/**
 * 
 * 合同管理
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-16 19:38:02
 */
@Slf4j
@RestController
@RequestMapping("emc/ContractManage")
public class ContractManageController  {
    @Autowired
    private ContractManageService contractManageService;
    

    /**
     *  合同基本信息录入
     * @param htvo
     * @return
     */
    @RequestMapping("/inputContract")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData inputContract(@RequestBody HtxxVo htvo){
        ReturnData ret=ReturnData.getSuccessData();
        try {
            //log.info("htvo=="+htvo);
            int result=contractManageService.addContract(htvo);
            if(result>0){
                ret.setMsg("录入成功");
            }
        } catch (Exception e) {
            log.error("录入失败，异常：{}", e);
            return ReturnData.getExceptionData("录入失败，异常");   
        }
        
        return ret;
    };

    /**
     * 订货明细录入
     * @param cpggVoList
     * @return
     */
    @RequestMapping("/inputDhmx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData inputDhmx(@RequestBody List<HtDhmxVo> cpggVoList){
        ReturnData ret=ReturnData.getSuccessData();
        try {
            for(HtDhmxVo cpggVo: cpggVoList){
                HtDhmx cpgg=new HtDhmx();
                cpgg.setHtSno(Integer.parseInt(cpggVo.getHtsno()));
                cpgg.setCpdh(Integer.parseInt(cpggVo.getCpdh()));   //产品代号
                cpgg.setHtlb(cpggVo.getHtlb());
                cpgg.setCpbh(cpggVo.getCpbm());     //产品编码
                cpgg.setJd((cpggVo.getJd()));
                cpgg.setSl((cpggVo.getSl()));
                cpgg.setDj((cpggVo.getCjj()));        //成交价
                cpgg.setJe((cpggVo.getRmbje()));      //人民币金额
                cpgg.setHl((cpggVo.getHl()));         //汇率
                cpgg.setWhje((cpggVo.getWhje()));     //外汇金额
                cpgg.setBz2(cpggVo.getBz2());   //备注

                log.info("cpgg===="+cpgg);
                int result=contractManageService.addDhmx(cpgg);
                if(result>0){
                    ret.setMsg("录入订货明细成功");
                }
            }
            
        } catch (Exception e) {
            log.error("录入订货明细失败，异常：{}", e);
            return ReturnData.getExceptionData("录入订货明细失败，异常");
        }

        return ret;
    };

    /**
     * 机组编号录入
     * @param jzxxList
     * @return
     */
    @RequestMapping("/inputJzbh")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData inputJzbh(@RequestBody List<HtJzbh> jzxxList){
        ReturnData ret=ReturnData.getSuccessData();
        try {
            for(HtJzbh jzxx: jzxxList){
                log.info(jzxx+"");
                
                int result=contractManageService.addJzbh(jzxx);
                if(result>0){
                    ret.setMsg("录入机组编号成功");
                }
            }

        } catch (Exception e) {
            log.error("录入机组编号失败，异常：{}", e);
            return ReturnData.getExceptionData("录入机组编号失败，异常");
        }

        return ret;
    };


    /**
     *  
     *  获取合同基本信息
     *  添加权限
     * @param htxxVo
     * @return
     */
    @RequestMapping("/getContractList")
//    @RequiresRoles(value = {"admin"},logical = Logical.AND)
    public ReturnData getContractList(@RequestBody HtxxVo htxxVo){
        if (htxxVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            List<HtxxVo> htVoList=contractManageService.getContractList(htxxVo);
            ret.setData(htVoList);
            ret.setMsg("查询成功");

        } catch (Exception e) {
            log.error("查询合同信息失败，异常：{}", e);
            return ReturnData.getExceptionData("查询合同信息失败，异常");
        }

        return ret;
    };






    /**
     *  根据合同编号获取合同基本信息
     * @param htbh
     * @return
     */
    @RequestMapping("/getContractByHtbh")
    public ReturnData getContractByHtbh(@RequestParam String htbh){
        if (htbh == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret=ReturnData.getSuccessData();
        try {
            Htxx htxx=contractManageService.getContractByHtbh(htbh);
            ret.setData(htxx);
            ret.setMsg("查询成功");

        } catch (Exception e) {
            log.error("查询合同信息失败，异常：{}", e);
            return ReturnData.getExceptionData("查询合同信息失败，异常");
        }
                          
        return ret;                 
    };

    /**
     *  根据合同序号获取金额情况
     * @param htsno
     * @return
     */
    @RequestMapping("/getJeqkList")
    public ReturnData getJeqkList(@RequestParam String htsno){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            HtJeqkVo htJeqkVo=contractManageService.getJeqkList(htsno);
            ret.setData(htJeqkVo);
            ret.setMsg("查询金额情况成功");

        } catch (Exception e) {
            log.error("查询合同收款失败，异常：{}", e);
            return ReturnData.getExceptionData("查询合同收款失败，异常");
        }

        return ret;
    };
    

    /**
     *  根据合同序号获取订货明细
     * @param htsno
     * @return
     */
    @RequestMapping("/getDhmxList")
    public ReturnData getDhmxList(@RequestParam String htsno){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            List<HtDhmxVo> cpggVoList=contractManageService.getDhmxList(htsno);
            ret.setData(cpggVoList);
            ret.setMsg("查询订货明细成功");

        } catch (Exception e) {
            log.error("查询订货明细失败，异常：{}", e);
            return ReturnData.getExceptionData("查询订货明细失败，异常");
        }

        return ret;
    };

    /**
     *  合同录入-订货明细录入(录入时)
     * @param htDhmxList
     * @return
     */
    @RequestMapping("/addDhmx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addDhmx(@RequestParam List<HtDhmx> htDhmxList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htDhmxList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for(HtDhmx htDhmx :htDhmxList){
                contractManageService.addDhmx(htDhmx);
            }
          
        } catch (Exception e) {
            log.error("新增订货明细失败，异常：{}", e);
            return ReturnData.getExceptionData("新增订货明细失败，异常");
        }

        return ret;
    };

    /**
     *  录入订货明细详细数据(管理时)
     * @param htDhmxVoList
     * @return
     */
    @RequestMapping("/addDhmxDetail")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addDhmxDetail(@RequestBody List<HtDhmxVo> htDhmxVoList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htDhmxVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        log.info("htDhmxVoList=="+htDhmxVoList);
        try {
            for(HtDhmxVo htDhmxVo :htDhmxVoList){
                contractManageService.addDhmxDetail(htDhmxVo);
            }

        } catch (Exception e) {
            log.error("录入订货明细失败，异常：{}", e);
            return ReturnData.getExceptionData("录入订货明细失败，异常");
        }

        return ret;
    };


    /**
     *  根据产品类型获取内部折扣比例
     * @param cplxid
     * @return
     */
    @RequestMapping("/queryNbzkbl")
    public ReturnData queryNbzkbl(@RequestParam String cplxid){
        ReturnData ret=ReturnData.getSuccessData();
        if (cplxid == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            Double num=contractManageService.queryNbzkblByCplxid(cplxid);
            ret.setData(num);
            ret.setMsg("查询内部折扣比例成功");

        } catch (Exception e) {
            log.error("查询内部折扣比例失败，异常：{}", e);
            return ReturnData.getExceptionData("查询内部折扣比例失败，异常");
        }

        return ret;
    };
    

    /**
     *  根据合同序号获取成本
     * @param htsno
     * @return
     */
    @RequestMapping("/getCbList")
    public ReturnData getCbList(@RequestParam String htsno){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            List<HtCb> cbVoList=contractManageService.getCbList(htsno);
            ret.setData(cbVoList);
            ret.setMsg("查询成本成功");

        } catch (Exception e) {
            log.error("查询成本失败，异常：{}", e);
            return ReturnData.getExceptionData("查询成本失败，异常");
        }

        return ret;
    };

    /**
     *  根据合同序号获取费用
     * @param htsno
     * @return
     */
    @RequestMapping("/getFyList")
    public ReturnData getFyList(@RequestParam String htsno){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            List<HtFyVo> htFyVoList=contractManageService.getFyList(htsno);
            ret.setData(htFyVoList);
            ret.setMsg("查询费用成功");

        } catch (Exception e) {
            log.error("查询费用失败，异常：{}", e);
            return ReturnData.getExceptionData("查询费用失败，异常");
        }

        return ret;
    };
    

    /**
     *  根据合同序号获取付款规定
     * @param htsno
     * @return
     */
    @RequestMapping("/getFkgdList")
    public ReturnData getFkgdList(@RequestParam String htsno){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            List<HtFkgd> htFkgdList=contractManageService.getFkgdList(htsno);
            ret.setData(htFkgdList);
            ret.setMsg("查询付款规定成功");

        } catch (Exception e) {
            log.error("查询付款规定失败，异常：{}", e);
            return ReturnData.getExceptionData("查询付款规定失败，异常");
        }

        return ret;
    };

    /**
     * 修改合同基本内容
     */
    @RequestMapping("/updateContract")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateContract(@RequestBody  HtxxVo htxxVo){
        ReturnData ret=ReturnData.getSuccessData();
        if (htxxVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            log.info("htxxVo=="+htxxVo);
            int result=contractManageService.updateContractByHtsno(htxxVo);
            if(result>0){
                ret.setMsg("已修改合同基本内容");
            }


        }catch (Exception e){
            log.error("合同基本内容异常，异常：{}", e);
            return ReturnData.getExceptionData("修改合同基本内容，异常");
        }

        return ret;
    }


    /**
     * 修改合同金额情况
     */
    @RequestMapping("/updateJeqk")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateJeqk(@RequestBody  HtJeqkVo htJeqkVo){
        ReturnData ret=ReturnData.getSuccessData();
        if (htJeqkVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            int result=contractManageService.updateJeqkByHtsno(htJeqkVo);
            if(result>0){
                ret.setMsg("已修改合同金额情况");
            }


        }catch (Exception e){
            log.error("合同基本内容异常，异常：{}", e);
            return ReturnData.getExceptionData("修改合同金额情况，异常");
        }

        return ret;
    }

    
    
    /**
     * 根据根据产品编号修改合同订货明细
     */
    @RequestMapping("/updateDhmx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateDhmx(@RequestBody  HtDhmxVo htDhmxVo){
        ReturnData ret=ReturnData.getSuccessData();
        if (htDhmxVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            log.info("htDhmxVo==="+htDhmxVo);
            int result=contractManageService.updateDhmxByCpbh(htDhmxVo);
            if(result>0){
                ret.setMsg("已修改合同订货明细");
            }


        }catch (Exception e){
            log.error("合同订货明细修改异常，异常：{}", e);
            return ReturnData.getExceptionData("修改合同订货明细，异常");
        }

        return ret;
    }

    /**
     * 根据根据产品代号删除合同订货明细
     */
    @RequestMapping("/delDhmx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delDhmx(@RequestBody  List<HtDhmxVo> htDhmxVoList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htDhmxVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            for(HtDhmxVo htDhmxVo :htDhmxVoList){
                int result=contractManageService.delDhmxByCpdh(htDhmxVo);
                if(result>0){
                    ret.setMsg("删除合同订货明细成功");
                }
            }

        }catch (Exception e){
            log.error("合同订货明细删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除合同订货明细，异常");
        }

        return ret;
    }

    /**
     * 根据合同序号htsno新增付款规定
     * @param fkgdList
     * @return
     */
    @RequestMapping("/addFkgd")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addFkgd(@RequestBody List<HtFkgd> fkgdList){
        ReturnData ret=ReturnData.getSuccessData();
        try {
            for(HtFkgd htFkgd: fkgdList){
                int result=contractManageService.addFkgd(htFkgd);
                if(result>0){
                    ret.setMsg("新增付款规定成功");
                }
            }

        } catch (Exception e) {
            log.error("新增付款规定失败，异常：{}", e);
            return ReturnData.getExceptionData("新增付款规定失败，异常");
        }

        return ret;
    };

    /**
     * 根据付款序号修改付款规定
     */
    @RequestMapping("/updateFkgd")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateFkgd(@RequestBody  HtFkgd htFkgd){
        ReturnData ret=ReturnData.getSuccessData();
        if (htFkgd == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            int result=contractManageService.updateFkgd(htFkgd);
            if(result>0){
                ret.setMsg("已修改付款规定");
            }


        }catch (Exception e){
            log.error("付款规定修改异常，异常：{}", e);
            return ReturnData.getExceptionData("修改付款规定，异常");
        }

        return ret;
    }


    /**
     * 根据付款序号删除合同付款规定
     */
    @RequestMapping("/delFkgd")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delFkgd(@RequestBody  List<HtFkgd> htFkgdList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htFkgdList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            for(HtFkgd htFkgd :htFkgdList){
                int result=contractManageService.delFkgd(htFkgd);
                if(result>0){
                    ret.setMsg("删除付款规定成功");
                }
            }

        }catch (Exception e){
            log.error("付款规定删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除付款规定，异常");
        }

        return ret;
    }



    /**
     * 根据htsno删除合同内容
     */
    @RequestMapping("/deleteContract")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData deleteContract(@RequestBody  List<String> htsnoList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsnoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            for(String htsno : htsnoList){
                contractManageService.deleteContract(htsno);
            }

            ret.setMsg("删除成功");

        }catch (Exception e){
            log.error("删除合同，异常：{}", e);
            return ReturnData.getExceptionData("删除合同，异常");
        }

        return ret;
    }


    /**
     *  根据合同序号获取机组信息
     * @param htsno
     * @return
     */
    @RequestMapping("/getJzxxList")
    public ReturnData getJzxxList(@RequestParam String htsno){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            List<HtJzxx> htJzxxList=contractManageService.getJzxxList(htsno);
            ret.setData(htJzxxList);
            ret.setMsg("查询机组信息成功");

        } catch (Exception e) {
            log.error("查询机组信息失败，异常：{}", e);
            return ReturnData.getExceptionData("查询机组信息失败，异常");
        }

        return ret;
    };

    /**
     * 根据合同序号htsno新增机组编号信息
     * @param htJzxxList
     * @return
     */
    @RequestMapping("/addJzxx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addJzxx(@RequestBody List<HtJzxx> htJzxxList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htJzxxList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for(HtJzxx htJzxx: htJzxxList){
                int result=contractManageService.addJzxx(htJzxx);
                if(result>0){
                    ret.setMsg("新增机组编号信息成功");
                }
            }

        } catch (Exception e) {
            log.error("新增机组编号信息失败，异常：{}", e);
            return ReturnData.getExceptionData("新增机组编号信息失败，异常");
        }

        return ret;
    };


    /**
     * 根据合同序号、录入时间修改机组信息
     */
    @RequestMapping("/updateJzxx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateJzxx(@RequestBody  HtJzxx htJzxx){
        ReturnData ret=ReturnData.getSuccessData();
        if (htJzxx == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            int result=contractManageService.updateJzxx(htJzxx);
            if(result>0){
                ret.setMsg("已修改机组编号信息");
            }


        }catch (Exception e){
            log.error("机组编号信息修改异常，异常：{}", e);
            return ReturnData.getExceptionData("修改机组编号信息，异常");
        }

        return ret;
    }


    /**
     * 根据合同序号、录入时间删除机组信息
     */
    @RequestMapping("/delJzxx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delJzxx(@RequestBody  List<HtJzxx> htJzxxList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htJzxxList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            for(HtJzxx htJzxx :htJzxxList){
                int result=contractManageService.delJzxx(htJzxx);
                if(result>0){
                    ret.setMsg("删除机组编号信息成功");
                }
            }

        }catch (Exception e){
            log.error("机组编号信息删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除机组编号信息，异常");
        }

        return ret;
    }

    /**
     * 根据合同序号htsno新增合同成本
     * @param htCbList
     * @return
     */
    @RequestMapping("/addCb")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addCb(@RequestBody List<HtCb> htCbList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htCbList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for(HtCb htCb: htCbList){
                int result=contractManageService.addCb(htCb);
                if(result>0){
                    ret.setMsg("新增合同成本信息成功");
                }
            }

        } catch (Exception e) {
            log.error("新增合同成本信息失败，异常：{}", e);
            return ReturnData.getExceptionData("新增合同成本信息失败，异常");
        }

        return ret;
    };

    /**
     * 根据合同序号、录入时间删除成本
     */
    @RequestMapping("/delCb")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delCb(@RequestBody  List<HtCb> htCbList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htCbList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            for(HtCb htCb :htCbList){
                int result=contractManageService.delCb(htCb);
                if(result>0){
                    ret.setMsg("删除成本信息成功");
                }
            }

        }catch (Exception e){
            log.error("成本信息删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除成本信息，异常");
        }

        return ret;
    }

    /**
     * 根据合同序号、录入时间修改成本
     */
    @RequestMapping("/updateCb")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateCb(@RequestBody  HtCb htcb){
        ReturnData ret=ReturnData.getSuccessData();
        if (htcb == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            int result=contractManageService.updateCb(htcb);
            if(result>0){
                ret.setMsg("已修改合同成本信息");
            }
            
        }catch (Exception e){
            log.error("合同成本信息修改异常，异常：{}", e);
            return ReturnData.getExceptionData("修改合同成本信息，异常");
        }

        return ret;
    }



    /**
     * 根据合同序号htsno新增费用
     * @param htFyVoList
     * @return
     */
    @RequestMapping("/addFy")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addFy(@RequestBody List<HtFyVo> htFyVoList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htFyVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for(HtFyVo htFyVo: htFyVoList){
                int result=contractManageService.addFy(htFyVo);
                if(result>0){
                    ret.setMsg("新增费用信息成功");
                }
            }

        } catch (Exception e) {
            log.error("新增费用信息失败，异常：{}", e);
            return ReturnData.getExceptionData("新增费用信息失败，异常");
        }

        return ret;
    };


    /**
     * 根据项目序号id删除费用
     */
    @RequestMapping("/delFy")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delFy(@RequestBody  List<HtFyVo> htFyVoList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htFyVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            for(HtFyVo htFyVo :htFyVoList){
                int result=contractManageService.delFy(htFyVo);
                if(result>0){
                    ret.setMsg("删除费用信息成功");
                }
            }

        }catch (Exception e){
            log.error("费用信息删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除费用信息，异常");
        }

        return ret;
    }


    /**
     * 根据项目序号id修改费用
     */
    @RequestMapping("/updateFy")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateFy(@RequestBody  HtFyVo htFyVo){
        ReturnData ret=ReturnData.getSuccessData();
        if (htFyVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            int result=contractManageService.updateFy(htFyVo);
            if(result>0){
                ret.setMsg("已修改费用信息");
            }

        }catch (Exception e){
            log.error("费用信息修改异常，异常：{}", e);
            return ReturnData.getExceptionData("费用成本信息，异常");
        }

        return ret;
    }

    /**
     *  根据合同序号htsno获取年服/水处理/节能改造
     * @param htsno
     * @return
     */
    @RequestMapping("/getNfList")
    public ReturnData getNfList(@RequestParam String htsno){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            List<HtNf> htNfList=contractManageService.getNfList(htsno);
            ret.setData(htNfList);
            ret.setMsg("查询年服/水处理/节能改造成功");

        } catch (Exception e) {
            log.error("查询年服/水处理/节能改造失败，异常：{}", e);
            return ReturnData.getExceptionData("查询年服/水处理/节能改造失败，异常");
        }

        return ret;
    };


    /**
     * 新增年服、水处理、技改
     * @param htNfList
     * @return
     */
    @RequestMapping("/addNf")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addNf(@RequestBody List<HtNf> htNfList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htNfList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for(HtNf htNf: htNfList){
                int result=contractManageService.addNf(htNf);
                if(result>0){
                    ret.setMsg("新增年服、水处理、技改成功");
                }
            }

        } catch (Exception e) {
            log.error("新增年服、水处理、技改失败，异常：{}", e);
            return ReturnData.getExceptionData("新增年服、水处理、技改信息失败，异常");
        }

        return ret;
    };

    /**
     * 根据服务序号fwSno修改年服、水处理、技改
     */
    @RequestMapping("/updateNf")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateNf(@RequestBody  HtNf htNf){
        ReturnData ret=ReturnData.getSuccessData();
        if (htNf == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            int result=contractManageService.updateNf(htNf);
            if(result>0){
                ret.setMsg("已修改年服、水处理、技改信息");
            }

        }catch (Exception e){
            log.error("年服、水处理、技改信息修改异常，异常：{}", e);
            return ReturnData.getExceptionData("年服、水处理、技改信息，异常");
        }

        return ret;
    }


    /**
     * 根据服务序号fwsno删除年服、水处理、技改
     */
    @RequestMapping("/delNf")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delNf(@RequestBody  List<HtNf> htNfList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htNfList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            for(HtNf htNf :htNfList){
                int result=contractManageService.delNf(htNf);
                if(result>0){
                    ret.setMsg("删除年服、水处理、技改信息成功");
                }
            }

        }catch (Exception e){
            log.error("年服、水处理、技改信息删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除年服、水处理、技改信息，异常");
        }

        return ret;
    }

    /**
     *  根据合同序号htsno获取合同收款
     * @param htsno
     * @return
     */
    @RequestMapping("/getHtskList")
    public ReturnData getHtskList(@RequestParam String htsno,@RequestParam String time){
        ReturnData ret=ReturnData.getSuccessData();
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            List<HtSkVo> fkbVoList=contractManageService.getHtskList(htsno,time);
            ret.setData(fkbVoList);
            ret.setMsg("查询合同收款成功");

        } catch (Exception e) {
            log.error("查询合同收款失败，异常：{}", e);
            return ReturnData.getExceptionData("查询合同收款失败，异常");
        }

        return ret;
    };


    /**
     *  收款数据拆分-保存拆分数据
     * @param u8Info
     * @return
     */
    @RequestMapping("/addSkSplitData")
    public ReturnData addSkSplitData(@RequestBody U8Info u8Info){
        ReturnData ret=ReturnData.getSuccessData();
        if (u8Info == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            int res=contractManageService.addSkCfData(u8Info);
            if(res>0){
                ret.setMsg("收款数据拆分成功");
            }
        } catch (Exception e) {
            log.error("收款数据拆分失败，异常：{}", e);
            return ReturnData.getExceptionData("收款数据拆分失败，异常");
        }

        return ret;
    };
    

    /**
     * 新增合同收款
     * @param htSkVoList
     * @return
     */
    @RequestMapping("/addHtSk")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addHtSk(@RequestBody List<HtSkVo> htSkVoList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htSkVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for(HtSkVo htSkVo: htSkVoList){
                int result=contractManageService.addHtSk(htSkVo);
                if(result>0){
                    ret.setMsg("新增合同收款信息成功");
                }
            }

        } catch (Exception e) {
            log.error("新增合同收款信息失败，异常：{}", e);
            return ReturnData.getExceptionData("新增合同收款信息失败，异常");
        }

        return ret;
    };


    /**
     * 根据收款序号skxh修改合同收款
     */
    @RequestMapping("/updateHtSk")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateHtSk(@RequestBody  HtSkVo htSkVo){
        ReturnData ret=ReturnData.getSuccessData();
        if (htSkVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            int result=contractManageService.updateHtSk(htSkVo);
            if(result>0){
                ret.setMsg("已修改合同收款信息");
            }

        }catch (Exception e){
            log.error("合同收款信息修改异常，异常：{}", e);
            return ReturnData.getExceptionData("修改合同收款信息，异常");
        }

        return ret;
    }

    /**
     * 根据收款序号skxh删除合同收款
     */
    @RequestMapping("/delHtSk")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delHtSk(@RequestBody  List<HtSkVo> htSkVoList){
        ReturnData ret=ReturnData.getSuccessData();
        if (htSkVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            for(HtSkVo htSkVo :htSkVoList){
                int result=contractManageService.delHtSk(htSkVo);
                if(result>0){
                    ret.setMsg("删除合同收款信息成功");
                }
            }

        }catch (Exception e){
            log.error("合同收款信息删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除合同收款信息，异常");
        }

        return ret;
    }







}
