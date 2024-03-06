package com.broad.emc.module.controller;


import com.broad.emc.module.entity.Cpxx;
import com.broad.emc.module.service.CpdmService;
import com.broad.emc.module.vo.CpxxVo;
import com.broad.emc.module.vo.ReturnData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


/**
 * 产品信息设置
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-19 10:25:48
 */
@Slf4j
@RestController
@RequestMapping("emc/cpdm")
public class CpdmController{
    @Autowired
    private CpdmService cpdmService;

    /**
     * 列表
     */
    @RequestMapping("/getList")
    public ReturnData getList(@RequestParam String nd,@RequestParam String cplx){
        ReturnData ret=ReturnData.getSuccessData();
        try {
            List<Cpxx> cpdmList =cpdmService.queryAll(nd,cplx);
            ret.setData(cpdmList);
        } catch (Exception e) {
            log.error("获取产品信息列表，异常：{}", e);
            return ReturnData.getExceptionData("获取产品信息列表，异常");
        }

        return ret;
    }

    /**
     * 新增产品信息
     */
    @RequestMapping("/addCpxx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addCpxx(@RequestBody List<CpxxVo> cpdmVoList){
        ReturnData ret=ReturnData.getSuccessData();
        try{
            
            for(CpxxVo cpdmVo : cpdmVoList){
                Cpxx cpdm=new Cpxx();
                cpdm.setCwnd(Integer.parseInt(cpdmVo.getNd()));
                cpdm.setCpbh(cpdmVo.getCpbm());
                cpdm.setCpgs(cpdmVo.getCpyjbm());   //产品业绩部门
                cpdm.setCpmc(cpdmVo.getCpmc());
                cpdm.setCpgg(cpdmVo.getCpgg());
                cpdm.setDw(cpdmVo.getDw());
                cpdm.setHtfylb(cpdmVo.getCplx());   //产品类型
                cpdm.setXmbh(cpdmVo.getXmbh());
                cpdm.setEszjf(cpdmVo.getEsj());     //二手机
                cpdm.setFydcpbz(cpdmVo.getFydcp()); //非远大产品
                cpdm.setBz(cpdmVo.getBz());
                cpdm.setBzbj(cpdmVo.getBzbj());
                cpdm.setTcbl(new BigDecimal(cpdmVo.getTcbl())); //提成比例
                cpdm.setCsrpolicy(cpdmVo.getGxzc());    //供需政策
                cpdm.setLrr(cpdmVo.getLrr());
                cpdm.setLrsj(cpdmVo.getLrsj());
    
                cpdmService.insertCpxx(cpdm);
            }
            

        }catch (Exception e){
            log.error("新增产品信息异常，异常：{}", e);
            return ReturnData.getExceptionData("新增产品信息，异常");
        }

        return ret;
    }

    /**
     * 删除产品信息
     */
    @RequestMapping("/delCpxx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delCpxx(@RequestBody List<CpxxVo> cpdmVoList){
        ReturnData ret=ReturnData.getSuccessData();

        try {
            for(CpxxVo cpdmVo : cpdmVoList){
                log.info(cpdmVo.getCpbm());
               int result= cpdmService.delCpxxByCpbm(cpdmVo.getCpbm());
               if(result>0){
                   ret.setMsg("删除成功");
               }
            }
            
        } catch (Exception e) {
            log.error("删除产品信息异常，异常：{}", e);
            return ReturnData.getExceptionData("删除产品信息，异常");       
        }

        return ret;
    }

    /**
     * 修改产品信息
     */
    @RequestMapping("/updateCpxx")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateCpxx(@RequestBody List<CpxxVo> cpdmVoList){
        ReturnData ret=ReturnData.getSuccessData();
        try{
            for(CpxxVo cpdmVo : cpdmVoList){
                Cpxx cpdm=new Cpxx();
                cpdm.setCwnd(Integer.parseInt(cpdmVo.getNd()));
                cpdm.setCpbh(cpdmVo.getCpbm());
                cpdm.setCpgs(cpdmVo.getCpyjbm());   //产品业绩部门
                cpdm.setCpmc(cpdmVo.getCpmc());
                cpdm.setCpgg(cpdmVo.getCpgg());
                cpdm.setDw(cpdmVo.getDw());
                cpdm.setHtfylb(cpdmVo.getCplx());   //产品类型
                cpdm.setXmbh(cpdmVo.getXmbh());
                cpdm.setEszjf(cpdmVo.getEsj());     //二手机
                cpdm.setFydcpbz(cpdmVo.getFydcp()); //非远大产品
                cpdm.setBz(cpdmVo.getBz());
                cpdm.setBzbj(cpdmVo.getBzbj());
                cpdm.setTcbl(new BigDecimal(cpdmVo.getTcbl()));
                cpdm.setCsrpolicy(cpdmVo.getGxzc());    //供需政策
                cpdm.setLrr(cpdmVo.getLrr());
                cpdm.setLrsj(cpdmVo.getLrsj());
                int result=cpdmService.updateCpxxByCpbm(cpdm);
                if(result>0){
                    ret.setMsg("修改成功");
                }
            }


        }catch (Exception e){
            log.error("修改产品信息异常，异常：{}", e);
            return ReturnData.getExceptionData("修改产品信息，异常");
        }

        return ret;
    }

    /**
     * 各个产品类型数量
     */
    @RequestMapping("/getNum")
    public ReturnData getNum(@RequestParam String cplx){
        ReturnData ret=ReturnData.getSuccessData();
        try {
            int num =cpdmService.queryNum(cplx);
            ret.setData(num);
        } catch (Exception e) {
            log.error("获取产品分类数量，异常：{}", e);
            return ReturnData.getExceptionData("获取产品分类数量，异常");
        }

        return ret;
    }

    
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{cpdh}")
//    public R info(@PathVariable("cpdh") Integer cpdh){
//			HtCpdmEntity htCpdm = htCpdmService.getById(cpdh);
//
//        return R.ok().put("htCpdm", htCpdm);
//    }
//

//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody HtCpdmEntity htCpdm){
//			htCpdmService.updateById(htCpdm);
//
//        return R.ok();
//    }
//


}
