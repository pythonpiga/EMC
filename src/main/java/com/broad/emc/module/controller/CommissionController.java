package com.broad.emc.module.controller;

import com.broad.emc.common.annotation.MyArchivesLog;

import com.broad.emc.module.entity.HtDb;
import com.broad.emc.module.entity.HtExcelData;
import com.broad.emc.module.entity.HtTcjg;
import com.broad.emc.module.service.CommissionService;
import com.broad.emc.module.service.ContractManageService;
import com.broad.emc.module.service.InfoService;
import com.broad.emc.module.vo.CommonVo;
import com.broad.emc.module.vo.HtxxVo;
import com.broad.emc.module.vo.HtywyVo;
import com.broad.emc.module.vo.ReturnData;
import io.lettuce.core.output.BooleanOutput;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import com.broad.emc.common.util.TimeUtil;


/**
 * 
 * 提成/算法设计
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-16 19:38:02
 */
@Slf4j
@RestController
@RequestMapping("emc/CommissionController")
public class CommissionController {

    @Resource
    private CommissionService commissionService;
    @Autowired
    private ContractManageService contractManageService;
    @Resource
    private InfoService infoService;


    /**
     * 获取合同提成人员及比例
     *
     * @param htsno
     * @return
     */
    @RequestMapping("/getTcry")
    public ReturnData getTcry(@RequestParam String htsno) {
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret = ReturnData.getSuccessData();
        try {
            List<HtywyVo> htywyVoList = commissionService.getTcry(htsno);
            ret.setData(htywyVoList);
            ret.setMsg("查询提成人员及比例成功");

        } catch (Exception e) {
            log.error("查询提成人员及比例失败，异常：{}", e);
            return ReturnData.getExceptionData("查询提成人员及比例失败，异常");
        }

        return ret;
    }

    /**
     * 获取合同提成人员历史变更记录
     *
     * @param htsno
     * @return
     */
    @RequestMapping("/getTcryHistoryList")
    public ReturnData getTcryHistoryList(@RequestParam String htsno) {
        if (htsno == null) {
            return ReturnData.getFailData("缺少参数");
        }
        ReturnData ret = ReturnData.getSuccessData();
        try {
            List<HtywyVo> htywyVoList = commissionService.getTcryHistoryList(htsno);
            ret.setData(htywyVoList);
            ret.setMsg("查询成人员历史变更记录成功");

        } catch (Exception e) {
            log.error("查询成人员历史变更记录失败，异常：{}", e);
            return ReturnData.getExceptionData("查询成人员历史变更记录失败，异常");
        }

        return ret;
    }

    /**
     * 新增提成人员及比例
     *
     * @param htywyVoList
     * @return
     */
    @RequestMapping("/addTcry")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData addTcry(@RequestBody List<HtywyVo> htywyVoList) {
        ReturnData ret = ReturnData.getSuccessData();
        if (htywyVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for (HtywyVo htywyVo : htywyVoList) {
                commissionService.addTcry(htywyVo);
            }

        } catch (Exception e) {
            log.error("新增提成人员失败，异常：{}", e);
            return ReturnData.getExceptionData("新增提成人员失败，异常");
        }

        return ret;
    }

    ;

    /**
     * 修改提成人员及比例
     */
    @RequestMapping("/updateTcry")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateTcry(@RequestBody HtywyVo htywyVo) {
        ReturnData ret = ReturnData.getSuccessData();
        if (htywyVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            //修改被继承者状态 flag=0
            int result = commissionService.updateTcry(htywyVo);
            if (result > 0) {
                int res=commissionService.addTcry(htywyVo);
                if(res>0){
                    ret.setMsg("已修改提成人员信息");
                }
            }
            
        } catch (Exception e) {
            log.error("修改提成人员信息异常，异常：{}", e);
            return ReturnData.getExceptionData("修改提成人员信息，异常");
        }

        return ret;
    }

    /**
     * 删除提成人员及比例
     */
    @RequestMapping("/delTcry")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData delTcry(@RequestBody List<HtywyVo> htywyVoList) {
        ReturnData ret = ReturnData.getSuccessData();
        if (htywyVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for (HtywyVo htywyVo : htywyVoList) {
                int result = commissionService.delTcry(htywyVo);
                if (result > 0) {
                    ret.setMsg("删除提成人员信息成功");
                }
            }

        } catch (Exception e) {
            log.error("提成人员信息删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除提成人员信息，异常");
        }

        return ret;
    }

    /**
     * 一键导入 新增/修改提成人员名单及比例
     */
    @RequestMapping("/updateTcryExcel")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateTcryExcel(@RequestBody List<HtywyVo> htywyVoList) {
        ReturnData ret = ReturnData.getSuccessData();
        if (htywyVoList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for (HtywyVo htywyVo : htywyVoList) {
                //根据合同名称找到对应的htsno
                HtxxVo htxxVo = new HtxxVo();
                htxxVo.setHtmc(htywyVo.getHtmc());
                List<HtxxVo> htxxVoList = contractManageService.getContractList1(htxxVo);
                //根据合同序号htsno查找提成人员信息
                List<HtywyVo> ywyList = commissionService.getTcry(htxxVoList.get(0).getHtsno());

                htywyVo.setHtsno(htxxVoList.get(0).getHtsno());
                //查询提成人所在的事务所
                HtywyVo htywy = infoService.getSwsByKhjl(htywyVo.getKhjl());
                htywyVo.setSws(htywy == null ? "" : htywy.getSwsId());

                if (ywyList.size() == 0) {
                    //没有提成人员则进行添加
                    commissionService.addTcry(htywyVo);
                } else {
                    Boolean flag = ywyList.stream().anyMatch(p -> p.getKhjl().equals(htywyVo.getKhjl()));
                    if (flag == false) {
                        //没有提成人员则进行添加
                        commissionService.addTcry(htywyVo);
                    } else {
                        //有提成人员则进行更新
                        commissionService.updateTcry(htywyVo);
                    }

                }


            }

        } catch (Exception e) {
            log.error("导入提成人员Excel异常，异常：{}", e);
            return ReturnData.getExceptionData("导入提成人员Excel，异常");
        }


        return ret;
    }


    /**
     * 一键导入 新增/修改合同手工导入数据
     */
    @RequestMapping("/updateHtExcel")
    @Transactional(rollbackFor = Exception.class)
    public ReturnData updateHtExcel(@RequestBody List<HtExcelData> htExcelDataList) {
        ReturnData ret = ReturnData.getSuccessData();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if (htExcelDataList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for (HtExcelData htExcelData : htExcelDataList) {
                //时间格式转换
                htExcelData.setQdrq(sdf.format( DateUtils.parseDate(htExcelData.getQdrq(), "yyyy-MM-dd") ) );
                htExcelData.setYyrq(sdf.format( DateUtils.parseDate(htExcelData.getYyrq(), "yyyy-MM-dd") ) );

                //根据合同名称找到对应的htsno
                HtxxVo htxxVo = new HtxxVo();
                htxxVo.setHtmc(htExcelData.getHtmc());
                List<HtxxVo> htxxVoList = contractManageService.getContractList1(htxxVo);
                htExcelData.setHtSno(htxxVoList.get(0).getHtsno());

                //根据合同序号htsno和year查找到对应的excel数据
                List<HtExcelData> excelData = commissionService.getHtExcelData(htxxVoList.get(0).getHtsno(),htExcelData.getYear());

                if (excelData.size() == 0) {
                    //没有该合同数据则进行添加
                    commissionService.addHtEecel(htExcelData);
                } else {
                    Boolean flag = excelData.stream().anyMatch(p -> p.getHtSno().equals(htExcelData.getHtSno()) & p.getYear().equals(htExcelData.getYear())   );
                    if (flag == false) {
                        //没有该合同数据则进行添加
                        commissionService.addHtEecel(htExcelData);
                    } else {
                        //有该合同数据则进行更新
                        commissionService.updateHtEecel(htExcelData);
                    }

                }

            }

        } catch (Exception e) {
            log.error("导入合同数据Excel异常，异常：{}", e);
            return ReturnData.getExceptionData("导入合同数据Excel，异常");
        }


        return ret;
    }

    /**
     * 根据合同序号 htsno 年份year 季度jd 查询对应的提成奖励信息
     * admin权限
     * @param htTcjg
     * @return
     */
    @RequestMapping("/getTcjg")
    @RequiresRoles("admin")
    public ReturnData getTcjg(@RequestBody HtTcjg htTcjg) {
        ReturnData ret = ReturnData.getSuccessData();
        try {
            List<HtTcjg> htTcjgList = commissionService.getTcjg(htTcjg);
            ret.setData(htTcjgList);
            ret.setMsg("查询提成奖励信息成功");
        } catch (Exception e) {
            log.error("查询提成奖励信息失败，异常：{}", e);
            return ReturnData.getExceptionData("查询提成奖励信息失败，异常");
        }
        
        return ret;
    }

    /**
     * 根据合同序号 htsno 年份year 季度jd 查询对应的提成奖励信息
     * admin权限
     * @param htTcjg
     * @return
     */
    @RequestMapping("/getTcjgList")
    @RequiresRoles("admin")
    public ReturnData getTcjgList(@RequestBody HtTcjg htTcjg) {
        ReturnData ret = ReturnData.getSuccessData();
        try {
            List<HtTcjg> htTcjgList = commissionService.getTcjgList(htTcjg);
            ret.setData(htTcjgList);
            ret.setMsg("查询提成奖励信息成功");
        } catch (Exception e) {
            log.error("查询提成奖励信息失败，异常：{}", e);
            return ReturnData.getExceptionData("查询提成奖励信息失败，异常");
        }

        return ret;
    }
    
    
    /**
     * 无奖励年份记录
     *
     * @param flag true 新增无奖励年份  false 清除已记录的无奖励年份
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
//    public void noRewardRecord(String htsno, String year, String jd, Boolean flag) throws ParseException {
//        //获得当期的上一年年份
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        Date dt = sdf.parse(year);
//        Calendar rightNow = Calendar.getInstance();
//        rightNow.setTime(dt);
//        rightNow.add(Calendar.YEAR, -1);// 日期减1年
//        Date dt1 = rightNow.getTime();
//        String LastYear = sdf.format(dt1);
//
//        //当前时间
//        String curmonth = "1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "";
//        String time = year + "-" + curmonth;
//
//        HtTcjg htTcjg = new HtTcjg();
//        htTcjg.setHtSno(htsno);
//        htTcjg.setYear(year);
//        htTcjg.setTcry("");
//        htTcjg.setWdbnf(flag == true ? LastYear : "");
//        htTcjg.setJd(flag == true ? jd : "");
//        htTcjg.setTime(time);
//
//        List<HtTcjg> tcjgInfo = commissionService.getTcjg(htTcjg);
//        if (tcjgInfo.size() == 0) {
//            //新增 记录未达标年份
//            commissionService.saveTcjg(htTcjg);
//        } else {
//            //更新 记录未达标年份
//            commissionService.updateTcjg(htTcjg);
//        }
//
//    }
    

    /**
     * 计算提成 大型EMC提成奖励 
     * admin权限
     * @MyArchivesLog 操作日志记录
     * @param year 年份 lx 奖励类型  jd  季度
     * @return
     */
    @RequestMapping("/EmcCommission")
    @RequiresRoles("admin")
    @MyArchivesLog(operteContent="大型EMC计算提成")
    public ReturnData EmcCommission(@RequestParam String year, @RequestParam String jd) {
        ReturnData ret = ReturnData.getSuccessData();
        if(Integer.parseInt(year)<2022){     //计算2022年以后的提成奖励
            ret.setMsg("该年份无需计算大型EMC提成奖励");
            return ret;
        }
        
        List<HtxxVo> list=new ArrayList<>();
        try {
            BigDecimal jjjs = null;        //当期累计计奖基数
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            //1. 获取所有合同信息
            List<HtxxVo> htxxVoList = contractManageService.getContractList1(null);
            for (HtxxVo htxxVo : htxxVoList) {
                //1.1 判断合同是否计算大型EMC奖励（大型emc奖励是指签订时间在2020.01.01之前的合同）
                if(htxxVo.getQdrq().after( sdf.parse("2020-01-01") )){
                    continue; //跳出，下一个合同
                }

                /**
                 * 重复计算是否计算（提成人员变更等特殊情况）
                 * 待解决
                 *
                //1.2 判断重复计算 （过滤掉已经当前时间已计算过的合同,已经计算过且提成人员未发生变动  ）
                HtTcjg tcjg =new HtTcjg();
                tcjg.setHtSno(htxxVo.getHtsno());
                tcjg.setYear(year);
                tcjg.setJd(jd);
                List<HtTcjg> tcjgList = commissionService.getTcjg(tcjg);
                if(tcjgList.size()>0){
                    log.info("+++++++++++++++++++++++" + htxxVo.getHtmc() + ",htsno==" + htxxVo.getHtsno()+"已计算过"+year+"第"+jd+"季度的奖励");
                    continue; //跳出，下一个合同
                }
                 */
                
                log.info("+++++++++++++++++++++++" + htxxVo.getHtmc() + ",htsno==" + htxxVo.getHtsno() + "开始计算大型EMC奖励,第" + year + "年,第" + jd + "季度，能耗合同标志：" + htxxVo.getSfnhzbht() + "++++++++++++++++++++++++++++++++++++++++++");
                
                //2.计算净利润率 （去年、当期） 去年净利润率(说明：不累计,算 上年1.1-上年12.31)
                //2.1 获取手工导入信息 （查询导入的excel  前期手工 后期自动取数）
                List<HtExcelData> htExcelDataList = commissionService.getHtExcelData(htxxVo.getHtsno(),year);
                if (htExcelDataList.size() == 0) {
                    log.info("=============该合同：" + htxxVo.getHtsno() + "无导入excel数据，无法计算，结束===================");
                    continue;   //进入下个合同计算
                }
                
                //2.2 获的上年跟当前的净利润率
                Map<String, Object> lrlInfo = getEmcAndDkLrl(htxxVo, year,jd);
                double LastLrl = (double) lrlInfo.get("LastLrl");
                double Lrl = (double) lrlInfo.get("Lrl");
                
                //3. 净利润率是否达标 （能耗合同/非能耗合同）
                //3.1 非能耗总包项目
                if ("0".equals(htxxVo.getSfnhzbht())) {
                    //3.1.1  非能耗 上年净利润率需要≥0.08
                    if (LastLrl < 0.08) {
                        //3.1.2 上年净利润率未达标特殊情况 （若是第四季度且当年净利润率达标8% 则给予奖励 否则无奖励 跳过此合同）
                        if (!"4".equals(jd) || Lrl < 0.08) {
                            log.info("=============上年非能耗合同净利润率：" + LastLrl + "<0.08,无奖励，结束===================");
                            continue;   //进入下个合同计算
                        }
                    }
                    
                    //3.1.3 当期净利润率需要>0.08
                    if (Lrl <= 0.08) {
                        log.info("=============当期非能耗合同净利润率：" + Lrl + "<=0.08,无奖励，结束===================");
                        continue;   //进入下个合同计算
                    }
                } else {
                    //3.2 能耗总包
                    //3.2.1 能耗总包 上年净利润率能耗总包需要≥0.05
                    if (LastLrl < 0.05) {
                        //3.2.2 上年净利润率未达标特殊情况 (若是第四季度且当年净利润率达标5% 则给予奖励 否则无奖励 跳过此合同)
                        if (!"4".equals(jd) || Lrl < 0.05) {
                            log.info("=============上年能耗总包项目净利润率：" + LastLrl + "<0.05,无奖励，结束===================");
                            continue;   //进入下个合同计算
                        }
                    }
                    //3.2.3 当期净利润率需要>0.05
                    if (Lrl <= 0.05) {
                        log.info("=============当期能耗总包项目净利润率：" + Lrl + "<=0.05,无奖励，结束计算===================");
                        continue;   //进入下个合同计算
                    }
                }
                
                //净利润率达标
                log.info("====================净利润达标，继续=========================");
                
                //4. 判断运营款是否收齐  （手工excel导入）
                if ("是".equals(htExcelDataList.get(0).getYyksqbz())) {
                    //5. 计算计奖基数 （计奖基数=到款-投资分摊（远大产品）-投资分摊（非远大产品）-年服-定金-赠送产品）
                    Map<String, Object> jjjsInfo = getEmcJjjs(htExcelDataList, htxxVo, year, jd);
                    jjjs = (BigDecimal) jjjsInfo.get("jjjs");

                    //6. 判断条件是否满足（运营时间>5年/续签项目 满足其一） 
                    Long times = null;
                    try {
                        Date star = sdf.parse(htxxVo.getYyqsrq());        //运营起始时间
                        Date endDay = sdf.parse(jjjsInfo.get("curtime").toString());
                        Long starTime = star.getTime();
                        Long endTime = endDay.getTime();
                        times = endTime - starTime;//时间戳相差的毫秒数
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    
                    //6.1 条件满足（运营时间>5 || 续签）
                    if (times / 24 / 60 / 60 / 1000 > 5 * 365 || "续签".equals(htxxVo.getXqhtbz())) {
                        log.info("=============当前项目运营时间大于5年/为续签合同,继续===================");
                        jjjs = jjjs.multiply(new BigDecimal(0.5));
                    }

                    //7. 获取该合同的提成人员及比例，计算结果保存   （手工导入excel提成人员）
                    List<HtywyVo> ywyList = commissionService.getTcry(htxxVo.getHtsno());
                    if(ywyList.size()>0){
                        //返回参与合同计算的合同信息
                        list.add(htxxVo);
                        ret.setData(list);
                    }

                    //7.1 提成计算及保存
                    String result=getEMCTcxx(htxxVo, year, jd,lrlInfo, jjjsInfo, Lrl, jjjs,htExcelDataList);

                    if("fail".equals(result)){
                        ret.setMsg("结果保存失败");
                        return ret;
                    }
                    
                } else {
                    log.info("=============当前合同运营款未齐全，无奖励，结束===================");
                }
                log.info("+++++++++++++++++++++当前合同结束计算+++++++++++++++++++++++++");
            }
            
            ret.setMsg("计算完成");

        } catch (Exception e) {
            log.error("计算失败，异常：{}", e);
            ret.setMsg("计算失败");
            return ReturnData.getExceptionData("计算失败，异常");
        }

        return ret;
    }

    

    /**
     * 计算提成 到款
     * admin权限
     * @MyArchivesLog 操作日志记录
     * @param year 年份 lx 奖励类型  jd  季度
     * @return
     */
    @RequestMapping("/DkCommission")
    @RequiresRoles("admin")
    @MyArchivesLog(operteContent="到款计算提成")
    public ReturnData DkCommission(@RequestParam String year, @RequestParam String jd) {
        ReturnData ret = ReturnData.getSuccessData();
        List<HtxxVo> list=new ArrayList<>();

        try {
            BigDecimal jjjs = null;        //当期累计计奖基数
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //到款奖励 （签订时间在2020.1.1-2021.7.1 及2021.7.1-至今）
            //1. 获取所有合同信息
            List<HtxxVo> htxxVoList = contractManageService.getContractList1(null);
            for (HtxxVo htxxVo : htxxVoList) {
                //1.1 判断合同是否计算到款奖励 （到款的签订时间在2020.01.01之后）
                if(htxxVo.getQdrq().before( sdf.parse("2020-01-01") )){
                    continue; //跳出，下一个合同
                }
                
                /**
                 * 重复计算是否计算（提成人员变更等特殊情况）
                 * 待解决
                 *
                 //1.2 判断重复计算 （过滤掉已经当前时间已计算过的合同,已经计算过且提成人员未发生变动  ）
                 HtTcjg tcjg =new HtTcjg();
                 tcjg.setHtSno(htxxVo.getHtsno());
                 tcjg.setYear(year);
                 tcjg.setJd(jd);
                 List<HtTcjg> tcjgList = commissionService.getTcjg(tcjg);
                 if(tcjgList.size()>0){
                     log.info("+++++++++++++++++++++++" + htxxVo.getHtmc() + ",htsno==" + htxxVo.getHtsno()+"已计算过"+year+"第"+jd+"季度的奖励");
                     continue; //跳出，下一个合同
                 }
                 */
                
                log.info("+++++++++++++++++++++++当前合同：" + htxxVo.getHtmc() + ",htsno==" + htxxVo.getHtsno() + "开始计算到款奖励,第" + year + "年,第" + jd + "季度，能耗合同标志：" + htxxVo.getSfnhzbht() + "++++++++++++++++++++++++++++++++++++++++++");
                
                //2.计算净利润率 （去年、当期） 去年净利润率(说明：不累计,算 上年1.1-上年12.31) 
                //2.1 获取手工导入信息 （查询导入的excel  前期手工 后期自动取数）
                List<HtExcelData> htExcelDataList = commissionService.getHtExcelData(htxxVo.getHtsno(),year);
                if (htExcelDataList.size() == 0) {
                    log.info("=============该合同：" + htxxVo.getHtsno() + "无导入excel数据，无法计算，结束===================");
                    continue;   //进入下一个合同计算
                }

                //2.2 获的上年跟当前的净利润率
                Map<String, Object> lrlInfo = getEmcAndDkLrl(htxxVo,year,jd);
                double LastLrl = (double) lrlInfo.get("LastLrl");
                double Lrl = (double) lrlInfo.get("Lrl");

                //3. 净利润率是否达标 （能耗合同/非能耗合同）
                //3.1 非能耗总包项目
                if ("0".equals(htxxVo.getSfnhzbht())) {        
                    //3.1.1 非能耗 上年净利润率需要≥0.08
                    if (LastLrl < 0.08) {
                        //3.1.2 上年净利润率未达标特殊情况 （若是第四季度且当年净利润率达标8% 则给予奖励 否则无奖励 跳过此合同）
                        if (!"4".equals(jd) || Lrl < 0.08) {
                            log.info("=============上年非能耗合同净利润率：" + LastLrl + "<0.08,无奖励，结束===================");
                            continue;   //进入下一个合同计算
                        }
                    }
                    //3.1.3 当期净利润率需要>0.08
                    if (Lrl <= 0.08) {
                        log.info("=============当期非能耗合同净利润率：" + Lrl + "<=0.08,无奖励，结束===================");
                        continue;   //进入下一个合同计算
                    }
                } else {      
                    //3.2 能耗总包
                    //3.2.1 能耗总包 上年净利润率能耗总包需要≥0.05
                    if (LastLrl < 0.05) {
                        //3.2.2 上年净利润率未达标特殊情况 (若是第四季度且当年净利润率达标5% 则给予奖励 否则无奖励 跳过此合同)
                        if (!"4".equals(jd) || Lrl < 0.05) {
                            log.info("=============上年能耗总包项目净利润率：" + LastLrl + "<0.05,无奖励，结束===================");
                            continue;   //进入下一个合同计算
                        }
                    }
                    //3.2.2 当期净利润率需要>0.05
                    if (Lrl <= 0.05) {
                        log.info("=============当期能耗总包项目净利润率：" + Lrl + "<=0.05,无奖励，结束计算===================");
                        continue;   //进入下一个合同计算
                    }
                }

                //净利润率达标
                log.info("====================净利润达标，继续=========================");

                //4. 运营款是否收齐           手工excel导入
                if("是".equals(htExcelDataList.get(0).getYyksqbz())) {
                    //5. 计算计奖基数
                    Map<String, Object> jjjsInfo = getDkJjjs(htExcelDataList, htxxVo, year, jd);
                    jjjs = (BigDecimal) jjjsInfo.get("jjjs");

                    //6. 判断条件是否满足（运营时间>5年/续签项目 满足其一） 
                    Long times = null;
                    try {
                        Date star = sdf.parse(htxxVo.getYyqsrq());        //运营起始时间
                        Date endDay = sdf.parse(jjjsInfo.get("curtime").toString());
                        Long starTime = star.getTime();
                        Long endTime = endDay.getTime();
                        times = endTime - starTime;//时间戳相差的毫秒数
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    
                    //6.1 条件满足（运营时间>5 || 续签）
                    if (times / 24 / 60 / 60 / 1000 > 5 * 365 || "续签".equals(htxxVo.getXqhtbz())) {
                        log.info("=============当前项目运营时间大于5年/为续签合同,继续===================");
                        jjjs = jjjs.multiply(new BigDecimal(0.5));
                    }
                    
                    //7. 获取该合同的提成人员及比例，计算结果保存   （手工导入excel提成人员）
                    List<HtywyVo> ywyList = commissionService.getTcry(htxxVo.getHtsno());
                    if(ywyList.size()>0){
                        //返回参与合同计算的合同信息
                        list.add(htxxVo);
                        ret.setData(list);
                    }

                    //7.1 提成计算及保存
                    String result=getDkTcxx(htxxVo, year, jd,lrlInfo, jjjsInfo, Lrl, jjjs,htExcelDataList);
                    
                    if("fail".equals(result)){
                        ret.setMsg("结果保存失败");
                        return ret;
                    }
                    
                }
                
                log.info("+++++++++++++++++++++当前合同结束计算+++++++++++++++++++++++++");
                
            }
            ret.setMsg("计算完成");
        } catch (Exception e) {
            log.error("计算失败，异常：{}", e);
            ret.setMsg("计算失败");
            return ReturnData.getExceptionData("计算失败，异常");
        }

        return ret;
    }

    
    
    
    
   
    

    /**
     * 到款
     * 前5年预留30%计奖基数
     * ps:去除未达标的年份数据
     * @param
     * @return
     */
//    public Map<String, Object> getDkJjjs5s(List<HtExcelData> htExcelDataList,LocalDate curtime,String year,String jd,String yyrq,HtxxVo htxxVo) throws ParseException {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Map<String ,Object> map=new HashMap<>();
//        BigDecimal jjjs5s=new BigDecimal( "0" );
//
//        //时间判断 2021.7.1-当前季度时间 是否有5年 否则不用计算
//        int num = TimeUtil.calculateMonthDifference(sdf.parse("2021-07-01").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), curtime);
//        if (num >= 5 * 12) {
//            // 计算前5年的净利润率
//            Map<String, Object> lrl5sInfo= getEmcAndDkLrl5s(htExcelDataList,htxxVo.getHtsno(),year,jd);
//            if ((double) lrl5sInfo.get("lrl5s") >= 0.08) {
//                log.info("=============当期能耗合同前5年净利润率：" + lrl5sInfo.get("lrl5s") + ">=0.08,开始计算前5年预留的30%奖励===================");
//
//                //ex: 计算2028第二季度预留的30%  时间应该是2023.4.1 - 2023.6.30 2023年的第二季度
//                //计算发放前5年对应季度剩余的30%  
//
//                //5年前的当前季度 2023 第二季度
//                String endTime=(Integer.parseInt(year) - 5)+"-"+("1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "");
//
//                //5年前的当前季度的前一个季度 2023 第一季度
//                String startTime=("1".equals(jd) ? (Integer.parseInt(year) - 6) : (Integer.parseInt(year) - 5))+"-"+
//                        ("1".equals(jd) ? "12-31" : "2".equals(jd) ? "03-31" : "3".equals(jd) ? "06-30" : "4".equals(jd) ? "09-30" : "");
//
//                //1. 起始时间的累计计奖基数 去除未达标的年份数据
//                BigDecimal dk_st = new BigDecimal("12606078.11");                                                                                        //累计截止至起始时间 去除未达标年份数据 待解决 U8客户明细账
//                BigDecimal nf_st  = new BigDecimal(htExcelDataList.get(0).getNf2021() == null ? "0" : htExcelDataList.get(0).getNf2021().toString());         //累计截止至起始时间 去除未达标年份数据 年服 excel取数
//                BigDecimal dj_st  = new BigDecimal(htExcelDataList.get(0).getDj2021() == null ? "0" : htExcelDataList.get(0).getDj2021().toString());        //累计截止至起始时间  去除未达标年份数据 定金 excel取数
//                BigDecimal zscp_st  = new BigDecimal(htExcelDataList.get(0).getZscp2021() == null ? "0" : htExcelDataList.get(0).getZscp2021().toString());   //累计截止至起始时间 去除未达标年份数据 赠送产品 excel取数
//
//                //运营时间
//                LocalDate yysj = sdf.parse(yyrq).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                LocalDate startDate = sdf.parse(startTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                //相差月数
//                int monthnum1 = TimeUtil.calculateMonthDifference(yysj, startDate);
//
//                //获取当前时间节点前未达标年份 
//                List<String> lists = commissionService.getWdbnf(htxxVo.getHtsno(), startTime);
//                //每有一年未达标 去掉该年的投资分摊
//                for (String item : lists) {
//                    if (item != null && !"".equals(item)) {
//                        monthnum1 = monthnum1 - 12;
//                    }
//                }
//                BigDecimal tzft_st = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum1)).toString());
//
//                //水电气抵扣额
//                List<HtExcelData> list = commissionService.getHtExcelData(htxxVo.getHtsno(),startTime.substring(0,4));
//                BigDecimal sdqdke_st=list.get(0).getSdqdke();
//                
//                //对运营款(到款)进行判断 到款<=1500 按50%计算
//                if (dk_st.compareTo(new BigDecimal("15000000")) <= 0) {
//                    dk_st = dk_st.multiply(new BigDecimal("0.5"));
//                }
//                
//                BigDecimal jjjs_st = dk_st.add(sdqdke_st).subtract(tzft_st).subtract(nf_st).subtract(dj_st).subtract(zscp_st);
//
//
//                //2. 结束时间的累计计奖基数 去除未达标的年份数据
//                BigDecimal dk_end = new BigDecimal("12606078.11");                                                                                        //累计截止至结束时间 去除未达标年份数据 待解决 U8客户明细账
//                BigDecimal nf_end = new BigDecimal(htExcelDataList.get(0).getNf2021() == null ? "0" : htExcelDataList.get(0).getNf2021().toString());         //累计截止至结束时间 去除未达标年份数据 年服 excel取数
//                BigDecimal dj_end  = new BigDecimal(htExcelDataList.get(0).getDj2021() == null ? "0" : htExcelDataList.get(0).getDj2021().toString());        //累计截止至结束时间  去除未达标年份数据 定金 excel取数
//                BigDecimal zscp_end  = new BigDecimal(htExcelDataList.get(0).getZscp2021() == null ? "0" : htExcelDataList.get(0).getZscp2021().toString());   //累计截止至结束时间 去除未达标年份数据 赠送产品 excel取数
//
//                LocalDate endDate = sdf.parse(endTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                //相差月数
//                int monthnum2 = TimeUtil.calculateMonthDifference(yysj, endDate);
//
//                //获取当前时间节点前未达标年份 
//                List<String> stringList = commissionService.getWdbnf(htxxVo.getHtsno(), endTime);
//                //每有一年未达标 去掉该年的投资分摊
//                for (String item : stringList) {
//                    if (item != null && !"".equals(item)) {
//                        monthnum2 = monthnum2 - 12;
//                    }
//                }
//                BigDecimal tzft_end = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum2)).toString());
//
//                //水电气抵扣额
//                List<HtExcelData> list_end = commissionService.getHtExcelData(htxxVo.getHtsno(),endTime.substring(0,4));
//                BigDecimal sdqdke_end=list_end.get(0).getSdqdke();
//
//                //对运营款(到款)进行判断 到款<=1500 按50%计算
//                if (dk_end.compareTo(new BigDecimal("15000000")) <= 0) {
//                    dk_end = dk_end.multiply(new BigDecimal("0.5"));
//                }
//                
//                BigDecimal jjjs_end = dk_end.add(sdqdke_end).subtract(tzft_end).subtract(nf_end).subtract(dj_end).subtract(zscp_end);
//
//                //前5年对应季度应发的预留30%
//                jjjs5s= ( jjjs_end.subtract(jjjs_st) ) .multiply( new BigDecimal("0.3" ) );
//                log.info("=============前5年剩余30%的计奖基数：" + jjjs5s + "===================");
//
//
//            }
//
//        }
//
//        map.put("jjjs5s",jjjs5s);
//        return map;
//
//    }


    


    /**
     * 净利润率计算
     * 记录历年净利润率达标情况(2022及以后)
     * 大型EMC、到款
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getEmcAndDkLrl(HtxxVo htxxVo, String year,String jd) throws ParseException {

        double LastLrl=0;                                //去年净利润率
        double Lrl=0;                                    //当期净利润率
        BigDecimal Sr=new BigDecimal(0);             //当期收入
        BigDecimal Nhcb=new BigDecimal(0);           //当期能耗成本
        BigDecimal Fy=new BigDecimal(0);             //当期费用

        //1. 记录历年净利润率达标情况(2022及以后)
        //1.1 遍历年份 获取2022年后净利润达标情况 （净利润率未达标的年份不参与计算只针对2022年及以后）
        for(int i=2022;i<=Integer.parseInt(year);i++ ){
            //1.2 获取该合同净利润率及年份信息
            HtDb htdb= commissionService.getDbInfo(htxxVo.getHtsno(),String.valueOf(i));
            //1.3. 获取指定年份各部分数据             
            BigDecimal sr=new BigDecimal (getU8Info(htxxVo.getHtsno(),String.valueOf(i),jd,0).get("sr") ) ;
            BigDecimal nhcb=new BigDecimal (getU8Info(htxxVo.getHtsno(),String.valueOf(i),jd,0).get("cb") ) ;
            BigDecimal fy=new BigDecimal (getU8Info(htxxVo.getHtsno(),String.valueOf(i),jd,0).get("fy") ) ;
            //BigDecimal sbft = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(12)).toString());

            double lrl;          //净利润率
            String isDb="0";     //是否达标 达标：1 未达标：0

            //1.4 计算指定年份净利润 （费用包含设备分摊总额）
            if ("0".equals(htxxVo.getSfnhzbht())) {        // 非能耗  （1-6%*12%）*85%
                lrl = (((sr.subtract(nhcb).subtract(fy)).multiply(new BigDecimal(0.9928)).multiply(new BigDecimal(0.85)))
                        .divide(sr, 4, BigDecimal.ROUND_HALF_UP)).doubleValue();
                if(lrl>=0.08)
                    isDb="1";
            } else {      // 能耗总包  （1-9%*12%）*85%）
                lrl = (((sr.subtract(nhcb).subtract(fy)).multiply(new BigDecimal(0.9892)).multiply(new BigDecimal(0.85)))
                        .divide(sr, 4, BigDecimal.ROUND_HALF_UP)).doubleValue();
                if(lrl>=0.05)
                    isDb="1";
            }
            
            if(i==Integer.parseInt(year)) {       //本年数据
                Lrl = lrl;
                Sr = sr;
                Nhcb = nhcb;
                Fy = fy;
            }
                
            if(i==Integer.parseInt(year)-1)     //去年净利润率
                LastLrl=lrl;

            //1.5 记录净利润率及年份信息
            HtDb htDb=new HtDb();
            htDb.setHtSno(htxxVo.getHtsno());
            htDb.setHtName(htxxVo.getHtmc());
            htDb.setYear(String.valueOf(i));
            htDb.setJlrl(BigDecimal.valueOf(lrl));
            htDb.setDb(isDb);

            if(htdb==null){
                System.out.println("执行保存");
                commissionService.saveDbInfo(htDb);
            }else{
                System.out.println("执行更新");
                commissionService.updateDbInfo(htDb);
            }
        }
        
        Map<String, Object> map = new HashMap<>();
        map.put("LastLrl", LastLrl);    //去年净利润率
        map.put("Lrl", Lrl);            //当期净利润率
        map.put("sr", Sr);              //当期收入 不累计 1.1-本季末
        map.put("nhcb", Nhcb);          //当期能耗成本 不累计 1.1-本季末
        map.put("fy", Fy);              //当期费用 不累计 1.1-本季末

        return map;


    }

    /**
     * 获取U8信息 
     * @param year 0:计算指定年份U8数据  1:计算历年累计U8数据  2:计算截止2021.12.31的累计U8数据  3:前5年累计U8数据  4:到款(客户明细账)的最后到款数据 5:指定年份的指定季度数据
     */
    public Map<String, String> getU8Info(String htsno, String year,String jd, int flag){
        BigDecimal sr = new BigDecimal("0");
        BigDecimal cb = new BigDecimal("0");
        BigDecimal fy = new BigDecimal("0");
        BigDecimal dk = new BigDecimal("0");
        String dksj="";

        Map<String,String> map=new HashMap<>();
        if(flag==1){
            //计算历年累计U8数据 去除未达标年份数据
            //查询未达标年份
            List<HtDb> htDbList = commissionService.getUnDbList (htsno);
            //测试
            dk=new BigDecimal("50519776.26");

            map.put("dk",dk.toString());

        }else if(flag==2){
            //计算截止2021.12.31的累计U8数据  无需去除未达标年份数据（2022年开始执行）
            //测试
            dk=new BigDecimal("42426448.26");
            map.put("dk",dk.toString());


        }else if(flag==3){
            //前5年累计U8数据  去除未达标年份数据（2022年开始执行）
        }else if(flag==4){
            //到款(客户明细账)的最后到款数据
            //测试
            dksj="2023-11-30";
            map.put("dksj",dksj);
        }else if(flag==5){
            //指定年份的指定季度数据 2023第2季度 2023.4.1-2023.6.30
            
        }else{
            //计算指定年份U8数据
            //当期数据使用jd
            //测试
            if("2022".equals(year)){
                sr=new BigDecimal("11110084.37");
                cb=new BigDecimal("7360096.76");
                fy=new BigDecimal("1897519.19");
            }else if("2023".equals(year)){
                sr=new BigDecimal("11123845.87");
                cb=new BigDecimal("7253020.29");
                fy=new BigDecimal("2289899.54");
            }

            map.put("sr",sr.toString());
            map.put("cb",cb.toString());
            map.put("fy",fy.toString());

        }

        return map;
    }


    /**
     * 大型EMC 计奖基数
     * 计奖基数 （累计：运营时间开始-当前季度截止 去除未达标的年份数据（2022及以后））
     * ps:去除未达标的年份数据（2022及以后）
     * @param
     * @return
     */
    public Map<String, Object> getEmcJjjs(List<HtExcelData> htExcelDataList, HtxxVo htxxVo, String year, String jd) throws ParseException {
        //1.计奖基数的数据 （计算计奖基数=到款-投资分摊-年服-定金-赠送产品 ，注意：取累计 运营时间开始-当前季度截止 去除未达标的年份数据）
        BigDecimal dk = new BigDecimal(getU8Info(htxxVo.getHtsno(),year,jd,1).get("dk"));         //当期累计到款(累计 运营时间开始-当前季度截止 去除未达标的年份数据) 待解决 U8客户明细账
        BigDecimal nf = new BigDecimal(htExcelDataList.get(0).getNf() == null ? "0" : htExcelDataList.get(0).getNf().toString());           //当期累计年服(累计到季，可取当年) excel取数
        BigDecimal dj = new BigDecimal(htExcelDataList.get(0).getDj() == null ? "0" : htExcelDataList.get(0).getDj().toString());           //当期累计定金(累计到季，可取当年) excel取数
        BigDecimal zscp = new BigDecimal(htExcelDataList.get(0).getZscp() == null ? "0" : htExcelDataList.get(0).getZscp().toString());     //当期累计赠送产品(累计到季，可取当年) excel取数

        //2.投资分摊计算
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //运营时间
        LocalDate yysj = sdf.parse(htxxVo.getYyqsrq()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //当前季度取数时间 U8客户明细账=到款 截止当前季度最后一次到款记录时间
        String dksj=getU8Info(htxxVo.getHtsno(),year,jd,4).get("dksj");
        LocalDate time = sdf.parse(dksj).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //相差月数
        int monthnum = TimeUtil.calculateMonthDifference(yysj, time);

        //当期累计投资分摊(累计到季)=(当前季度日期-运营起始日期)的月份*设备分摊(月)   excel取数 但是要去除不达标的年份数据
        //获取未达标年份 
        List<HtDb> htDbList = commissionService.getUnDbList (htxxVo.getHtsno());
        if(htDbList.size()>0){
            //每有一年未达标 去掉该年的投资分摊
            for (HtDb db : htDbList) {
                monthnum = monthnum - 12;
            }
        }

        BigDecimal tzft = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum)).toString());

        //3.计奖基数
        BigDecimal jjjs = dk.subtract(tzft).subtract(nf).subtract(dj).subtract(zscp);
        
        //当前季度时间
        String curmonth = "1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "";
        String curtime = year + "-" + curmonth;

        Map<String, Object> map = new HashMap<>();
        map.put("jjjs", jjjs);           //累计计奖基数 不含未达标年份数据
        map.put("dk", dk);               //当期累计到款 不含未达标年份数据
        map.put("tzft", tzft);           //当期累计投资分摊 不含未达标年份数据
        map.put("nf", nf);               //当期累计年服 不含未达标年份数据
        map.put("dj", dj);               //当期累计定金 不含未达标年份数据
        map.put("zscp", zscp);           //当期累计到赠送产品 不含未达标年份数据
        map.put("yysj", yysj);           //运营时间 
        map.put("curtime", curtime);     //当期时间

        return map;
    }

    /**
     * 到款奖励 计奖基数
     * 计奖基数 （累计：运营时间开始-当前季度截止 去除未达标的年份数据（2022及以后））
     * ps:去除未达标的年份数据 （2022及以后）
     * @param
     * @return
     */
    public Map<String, Object> getDkJjjs(List<HtExcelData> htExcelDataList, HtxxVo htxxVo, String year, String jd) throws ParseException {
        //1. 计奖基数的数据 （计算计奖基数=到款(100%/50%)+水电气抵扣额(80%excel已计算)-投资分摊-年服-定金-赠送产品)       注意：取累计 运营时间开始-当前季度截止 去除未达标的年份数据
        BigDecimal dk = new BigDecimal(getU8Info(htxxVo.getHtsno(),year,jd,1).get("dk"));                                               //当期累计到款(累计 运营时间开始-当前季度截止 去除未达标的年份数据) 待解决 U8客户明细账
        //1.1. 到款计数 （判断时间 2021.7.1）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(htxxVo.getQdrq().after( sdf.parse("2021-06-30") )){      //before/after 不包含当天
            if( dk.compareTo(new BigDecimal(15000000)) <=0 ){          //小于1500w
                dk=dk.multiply(new BigDecimal(0.5));
            }
        }
        //1.2 水电气抵扣额 (excel已计算80%)
        BigDecimal sdqdke=new BigDecimal( htExcelDataList.get(0).getSdqdke() == null ? "0" : htExcelDataList.get(0).getSdqdke().toString()  );      //当期累计水电气抵扣额 excel取数
        
        BigDecimal nf = new BigDecimal(htExcelDataList.get(0).getNf() == null ? "0" : htExcelDataList.get(0).getNf().toString());                   //当期累计年服(累计到季，可取当年) excel取数
        BigDecimal dj = new BigDecimal(htExcelDataList.get(0).getDj() == null ? "0" : htExcelDataList.get(0).getDj().toString());                   //当期累计定金(累计到季，可取当年) excel取数
        BigDecimal zscp = new BigDecimal(htExcelDataList.get(0).getZscp() == null ? "0" : htExcelDataList.get(0).getZscp().toString());             //当期累计赠送产品(累计到季，可取当年) excel取数

        //2.投资分摊计算
        //运营时间
        LocalDate yysj = sdf.parse(htxxVo.getYyqsrq()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //当前季度取数时间 U8客户明细账=到款 截止当前季度最后一次到款记录时间
        String dksj=getU8Info(htxxVo.getHtsno(),year,jd,4).get("dksj");
        LocalDate time = sdf.parse(dksj).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //相差月数
        int monthnum = TimeUtil.calculateMonthDifference(yysj, time);

        //当期累计投资分摊(累计到季)=(当前季度日期-运营起始日期)的月份*设备分摊(月)   excel取数 但是要去除不达标的年份数据
        //获取未达标年份 
        List<HtDb> htDbList = commissionService.getUnDbList (htxxVo.getHtsno());
        if(htDbList.size()>0){
            //每有一年未达标 去掉该年的投资分摊
            for (HtDb db : htDbList) {
                monthnum = monthnum - 12;
            }
        }
        BigDecimal tzft = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum)).toString());

        //3. 计奖基数
        BigDecimal jjjs = dk.add(sdqdke).subtract(tzft).subtract(nf).subtract(dj).subtract(zscp);

        //当前季度时间
        String curmonth = "1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "";
        String curtime = year + "-" + curmonth;
        
        Map<String, Object> map = new HashMap<>();
        map.put("jjjs", jjjs);           //累计计奖基数 不含未达标年份数据
        map.put("dk", dk);               //当期累计到款 不含未达标年份数据
        map.put("sdqdke", sdqdke);       //当期累计水电气抵扣额的80% 不含未达标年份数据
        map.put("tzft", tzft);           //当期累计投资分摊 不含未达标年份数据
        map.put("nf", nf);               //当期累计年服 不含未达标年份数据
        map.put("dj", dj);               //当期累计定金 不含未达标年份数据
        map.put("zscp", zscp);           //当期累计到赠送产品 不含未达标年份数据
        map.put("yysj", yysj);           //运营时间 
        map.put("curtime", curtime);     //当期时间

        return map;
    }
    

    /**
     * 大型EMC
     * 计算、保存提成信息
     *
     */
    public String getEMCTcxx(HtxxVo htxxVo,String year,String jd,Map<String, Object> lrlInfo,Map<String, Object> jjjsInfo,double Lrl,BigDecimal jjjs,List<HtExcelData> htExcelDataList) throws ParseException {

        BigDecimal jjjs5s=null;        //前5年计奖基数30%
        String yffjl= htExcelDataList.get(0).getYffjl2021();    //已发放金额

        //1. 根据合同序号htsno查找提成人员信息
        List<HtywyVo> ywyList = commissionService.getTcry(htxxVo.getHtsno());
        if(ywyList.size()>0){   //无提成人员，结束
            String curtime =jjjsInfo.get("curtime").toString();
            for (HtywyVo htywyVo : ywyList) {
                //2. 将所有计算结果存到数据库
                HtTcjg htTcjg = new HtTcjg();
                htTcjg.setHtSno(htxxVo.getHtsno());
                htTcjg.setYear(year);
                htTcjg.setJd(jd);
                htTcjg.setSr((BigDecimal) lrlInfo.get("sr"));
                htTcjg.setCb((BigDecimal) lrlInfo.get("nhcb"));
                htTcjg.setSbft((BigDecimal) lrlInfo.get("sbft"));
                htTcjg.setFy((BigDecimal) lrlInfo.get("fy"));
                htTcjg.setNf((BigDecimal) jjjsInfo.get("nf"));
                htTcjg.setDk((BigDecimal) jjjsInfo.get("dk"));
                htTcjg.setDj((BigDecimal) jjjsInfo.get("dj"));
                htTcjg.setZscp((BigDecimal) jjjsInfo.get("zscp"));
                htTcjg.setZscp((BigDecimal) jjjsInfo.get("tzft"));
                htTcjg.setJlrl(new BigDecimal(Lrl).setScale(4, BigDecimal.ROUND_DOWN));
                htTcjg.setTcry(htywyVo.getKhjl());
                htTcjg.setTime(curtime);
                htTcjg.setType("EMC");
                htTcjg.setHtmc(htxxVo.getHtmc());
                htTcjg.setHtbh(htxxVo.getHtbh());
                htTcjg.setSdq(htExcelDataList.get(0).getSdqdke());
                htTcjg.setSbft((BigDecimal) jjjsInfo.get("tzft"));

                BigDecimal tcje;                                //提成金额
                String tcbl="1.0";                              //提成比例
                BigDecimal yffjl2021=new BigDecimal(0);     //截止2021.12.31已发放奖励
                
                //在职状态 0 离职 1 在职 2 调岗 3继任
                if("0".equals( htywyVo.getType() ) || "2".equals( htywyVo.getType() ) || "3".equals( htywyVo.getType() )  ){
                    //人员类型
                    htTcjg.setRylx(htywyVo.getRylb());
                    //提成比例
                    htTcjg.setTcbl(new BigDecimal(0));
                    //提成金额
                    htTcjg.setTcje(new BigDecimal(0));
                    htTcjg.setTcje5s(new BigDecimal(0));

                }else{  //在职
                    if ( !"".equals(htywyVo.getTcbl())) {
                        tcbl = htywyVo.getTcbl();
                    }

                    /**
                     *  选择的时间在2022年及以后 2021.12.31之前100%计算 之后部分70%计算
                     *  
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    if(sdf.parse(curtime).after(sdf.parse("2021-12-31"))){
                        flag=true;
                    }
                     */

                    //1. 获取各提成人员对应的已发放奖励
                    for(int i=0;i<yffjl.split("，").length;i++){
                        String tcry=yffjl.split("，")[i].split("：")[0];
                        if(tcry.equals( htywyVo.getKhjl() )){
                            yffjl2021=new BigDecimal( yffjl.split("，")[i].split("：")[1] );
                        }
                    }
                    
                    String qdry="";
                    String percent="";
                    if( "签单客户经理".equals(htywyVo.getRylb()) ){
                        qdry="签单客户经理";
                        percent="0.01";
                    }else{
                        qdry="主管";
                        percent="0.002";
                    }

                    //2. 计算提成奖励
                    if ("0".equals(htxxVo.getSfnhzbht())) {
                        //2.1 非能耗总包(奖励=计奖基数*1% || 计奖基数*0.2%)
                        tcje = jjjs.multiply(new BigDecimal(tcbl)).multiply(new BigDecimal(percent));
                    }else {
                        //2.2 能耗总包2021.12.31之后(奖励=计奖基数*1%*70% )
                        //2.2.1 计算总计奖基数提成奖励（运营起始日期-当期季度）
                        BigDecimal ztcje = jjjs.multiply(new BigDecimal(tcbl)).multiply(new BigDecimal(percent));

                        //提成金额=已发放奖励+（2022-当前季度奖励）*70%
                        tcje=yffjl2021.add(  (ztcje.subtract(yffjl2021)).multiply(new BigDecimal(0.7))  );

                        //2.3 计算前5年是否发放剩余的30%(能耗总包)
                        Map<String, Object> emcJjjs5s= getEmcJjjs5s(htExcelDataList, LocalDate.parse(jjjsInfo.get("curtime").toString()), year, jd,htxxVo.getYyqsrq(),htxxVo.getHtsno());
                        jjjs5s= (BigDecimal) emcJjjs5s.get("jjjs5s");
                        BigDecimal tcje5s=jjjs5s.multiply(new BigDecimal(tcbl)).multiply(new BigDecimal(0.01));

                        htTcjg.setTcje5s( tcje5s.setScale(4, BigDecimal.ROUND_DOWN) );
                    }
                    
                    //人员类型
                    htTcjg.setRylx(qdry);
                    //提成比例
                    htTcjg.setTcbl(new BigDecimal(tcbl));
                    //提成金额
                    htTcjg.setTcje(tcje.setScale(4, BigDecimal.ROUND_DOWN));
                   
                }
                //操作时间
                htTcjg.setOpTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                //3. 查询该合同是否已经计算过提成奖励  （更新/新增）
                HtTcjg tcjgInfo = commissionService.getTcjgInfo(htTcjg);
                if (tcjgInfo == null) {
                    //4.1 未计算提成 （新增）
                    try {
                        int res = commissionService.saveTcjg(htTcjg);
                        if (res > 0) {
                            log.info("==============计算完成，保存成功==========");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        log.info("=============该合同：" + htxxVo.getHtsno() + "保存到数据库失败，结束===================");
                        return "fail";
                    }
                } else {
                    //4.2 已计算提成 （更新）
                    try {
                        int res = commissionService.updateTcjg(htTcjg);
                        if (res > 0) {
                            log.info("==============计算完成，更新成功==========");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.info("=============该合同：" + htxxVo.getHtsno() + "更新数据库失败，结束===================");
                        return "fail";
                    }
                }
            }

        }

        return "success";
    }

    /**
     * 到款
     * 计算、保存提成信息k
     *
     */
    public String getDkTcxx(HtxxVo htxxVo,String year,String jd,Map<String, Object> lrlInfo,Map<String, Object> jjjsInfo,double Lrl,BigDecimal jjjs,List<HtExcelData> htExcelDataList) throws ParseException {

        BigDecimal jjjs5s=null;                                 //前5年计奖基数30%
        String yffjl= htExcelDataList.get(0).getYffjl2021();    //已发放金额
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        
        //根据合同序号htsno查找提成人员信息
        List<HtywyVo> ywyList = commissionService.getTcry(htxxVo.getHtsno());
        if(ywyList.size()>0){   //无提成人员，结束
            String curtime =jjjsInfo.get("curtime").toString();
            for (HtywyVo htywyVo : ywyList) {
                //2. 将所有计算结果存到数据库
                HtTcjg htTcjg = new HtTcjg();
                htTcjg.setHtSno(htxxVo.getHtsno());
                htTcjg.setYear(year);
                htTcjg.setJd(jd);
                htTcjg.setSr((BigDecimal) lrlInfo.get("sr"));
                htTcjg.setCb((BigDecimal) lrlInfo.get("nhcb"));
                htTcjg.setSbft((BigDecimal) lrlInfo.get("sbft"));
                htTcjg.setFy((BigDecimal) lrlInfo.get("fy"));
                htTcjg.setNf((BigDecimal) jjjsInfo.get("nf"));
                htTcjg.setDk((BigDecimal) jjjsInfo.get("dk"));
                htTcjg.setDj((BigDecimal) jjjsInfo.get("dj"));
                htTcjg.setZscp((BigDecimal) jjjsInfo.get("zscp"));
                htTcjg.setZscp((BigDecimal) jjjsInfo.get("tzft"));
                htTcjg.setJlrl(new BigDecimal(Lrl).setScale(4, BigDecimal.ROUND_DOWN));
                htTcjg.setTcry(htywyVo.getKhjl());
                htTcjg.setTime(curtime);
                htTcjg.setType("DK");
                htTcjg.setHtmc(htxxVo.getHtmc());
                htTcjg.setHtbh(htxxVo.getHtbh());
                htTcjg.setSdq(htExcelDataList.get(0).getSdqdke());
                htTcjg.setSbft((BigDecimal) jjjsInfo.get("tzft"));
                
                BigDecimal tcje;                                //提成金额
                String tcbl="1.0";                              //提成比例
                BigDecimal yffjl2021=new BigDecimal(0);     //截止2021.12.31已发放奖励
                
                
                //2021.7.1之前继任调岗无奖励  （在职状态 0 离职 1 在职 2 调岗 3继任）
                if( ("0".equals( htywyVo.getType() ) || "2".equals( htywyVo.getType() ) || "3".equals( htywyVo.getType() )) 
                    && (htxxVo.getQdrq().before( sdf.parse("2021-07-01") ) )  ){
                    //人员类型
                    htTcjg.setRylx(htywyVo.getRylb());
                    //提成比例
                    htTcjg.setTcbl(new BigDecimal(0));
                    //提成金额
                    htTcjg.setTcje(new BigDecimal(0));
                    htTcjg.setTcje5s(new BigDecimal(0));

                }else{  //在职
                    if ( !"".equals(htywyVo.getTcbl())) {
                        tcbl = htywyVo.getTcbl();
                    }

                    /**
                     *  选择的时间在2022年及以后 2021.12.31之前100%计算 之后部分70%计算
                     *
                     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                     if(sdf.parse(curtime).after(sdf.parse("2021-12-31"))){
                     flag=true;
                     }
                     */

                    //1. 获取各提成人员对应的已发放奖励
                    for(int i=0;i<yffjl.split("，").length;i++){
                        String tcry=yffjl.split("，")[i].split("：")[0];
                        if(tcry.equals( htywyVo.getKhjl() )){
                            yffjl2021=new BigDecimal( yffjl.split("，")[i].split("：")[1] );
                        }
                    }

                    String qdry="";
                    String percent="";
                    if( "签单客户经理".equals(htywyVo.getRylb()) ){
                        qdry="签单客户经理";
                        percent="0.01";
                    }else{
                        qdry="主管";
                        percent="0.002";
                    }

                    //2. 计算提成奖励
                    if ("0".equals(htxxVo.getSfnhzbht())) {
                        //2.1 非能耗总包(奖励=计奖基数*1% || 计奖基数*0.2%)
                        tcje = jjjs.multiply(new BigDecimal(tcbl)).multiply(new BigDecimal(percent));
                    }else {
                        //2.2 能耗总包2021.12.31之后(奖励=计奖基数*1%*70% )
                        //2.2.1 计算总计奖基数提成奖励（运营起始日期-当期季度）
                        BigDecimal ztcje = jjjs.multiply(new BigDecimal(tcbl)).multiply(new BigDecimal(percent));

                        //提成金额=已发放奖励+（2022-当前季度奖励）*70%
                        tcje=yffjl2021.add(  (ztcje.subtract(yffjl2021)).multiply(new BigDecimal(0.7))  );

                        //2.3 计算前5年是否发放剩余的30%(能耗总包)
                        Map<String, Object> emcJjjs5s= getEmcJjjs5s(htExcelDataList, LocalDate.parse(jjjsInfo.get("curtime").toString()), year, jd,htxxVo.getYyqsrq(),htxxVo.getHtsno());
                        jjjs5s= (BigDecimal) emcJjjs5s.get("jjjs5s");
                        BigDecimal tcje5s=jjjs5s.multiply(new BigDecimal(tcbl)).multiply(new BigDecimal(0.01));

                        htTcjg.setTcje5s( tcje5s.setScale(4, BigDecimal.ROUND_DOWN) );
                    }

                    //人员类型
                    htTcjg.setRylx(qdry);
                    //提成比例
                    htTcjg.setTcbl(new BigDecimal(tcbl));
                    //提成金额
                    htTcjg.setTcje(tcje.setScale(4, BigDecimal.ROUND_DOWN));

                }
                //操作时间
                htTcjg.setOpTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                //3. 查询该合同是否已经计算过提成奖励  （更新/新增）
                HtTcjg tcjgInfo = commissionService.getTcjgInfo(htTcjg);
                if (tcjgInfo == null) {
                    //4.1 未计算提成 （新增）
                    try {
                        int res = commissionService.saveTcjg(htTcjg);
                        if (res > 0) {
                            log.info("==============计算完成，保存成功==========");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        log.info("=============该合同：" + htxxVo.getHtsno() + "保存到数据库失败，结束===================");
                        return "fail";
                    }
                } else {
                    //4.2 已计算提成 （更新）
                    try {
                        int res = commissionService.updateTcjg(htTcjg);
                        if (res > 0) {
                            log.info("==============计算完成，更新成功==========");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.info("=============该合同：" + htxxVo.getHtsno() + "更新数据库失败，结束===================");
                        return "fail";
                    }
                }
            }

        }

        return "success";
    }

    
    
    /**
     * 大型EMC（能耗总包）
     * 前5年预留30%计奖基数
     * ps:去除未达标的年份数据
     * @param
     * @return
     */
    public Map<String, Object> getEmcJjjs5s(List<HtExcelData> htExcelDataList,LocalDate curtime,String year,String jd,String yyrq,String htSno) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Map<String ,Object> map=new HashMap<>();
        BigDecimal jjjs5s=new BigDecimal( "0" );

        //1. 判断时间差是否有5年 （2021.12.31-当前季度时间 是否有5年 否则不用计算）
        int num = TimeUtil.calculateMonthDifference(sdf.parse("2021-12-31").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), curtime);
        if (num >= 5 * 12) {
            //2. 计算前5年的净利润率
            Map<String, Object> lrl5sInfo= getEmcAndDkLrl5s(htExcelDataList,htSno,year,jd);

            if ((double) lrl5sInfo.get("lrl5s") >= 0.08) {
                log.info("=============当期能耗合同前5年净利润率：" + lrl5sInfo.get("lrl5s") + ">=0.08,开始计算前5年预留的30%奖励===================");

                //ex: 计算2028第二季度前5年预留的30% =2023年的第二季度的30%，时间应该是2023.4.1 - 2023.6.30
                String year5s=String.valueOf(Integer.parseInt(year) - 5);
                BigDecimal dk5s=new BigDecimal( getU8Info(htSno,year5s,jd,5).get("dk")  );
                //忽略一个季度的年服、赠送产品、定金 
                BigDecimal tzft5s = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(3)).toString());

                jjjs5s = dk5s.subtract(tzft5s);
                log.info("=============前5年剩余30%的计奖基数：" + jjjs5s + "===================");

                /**
                 * v1.0 计算jjjs5s
                 *
                //5年前的当前季度 2023 第二季度
                String endTime=(Integer.parseInt(year) - 5)+"-"+("1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "");

                //5年前的当前季度的前一个季度 2023 第一季度
                String startTime=("1".equals(jd) ? (Integer.parseInt(year) - 6) : (Integer.parseInt(year) - 5))+"-"+
                        ("1".equals(jd) ? "12-31" : "2".equals(jd) ? "03-31" : "3".equals(jd) ? "06-30" : "4".equals(jd) ? "09-30" : "");

                //起始时间的累计计奖基数 去除未达标的年份数据
                BigDecimal dk_st = new BigDecimal(getU8Info(htSno,year,jd,1).get("dk"));                                                                                        //累计截止至起始时间 去除未达标年份数据 待解决 U8客户明细账
                BigDecimal nf_st  = new BigDecimal(htExcelDataList.get(0).getNf2021() == null ? "0" : htExcelDataList.get(0).getNf2021().toString());         //累计截止至起始时间 去除未达标年份数据 年服 excel取数
                BigDecimal dj_st  = new BigDecimal(htExcelDataList.get(0).getDj2021() == null ? "0" : htExcelDataList.get(0).getDj2021().toString());        //累计截止至起始时间  去除未达标年份数据 定金 excel取数
                BigDecimal zscp_st  = new BigDecimal(htExcelDataList.get(0).getZscp2021() == null ? "0" : htExcelDataList.get(0).getZscp2021().toString());   //累计截止至起始时间 去除未达标年份数据 赠送产品 excel取数

                //运营时间
                LocalDate yysj = sdf.parse(yyrq).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate startDate = sdf.parse(startTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //相差月数
                int monthnum1 = TimeUtil.calculateMonthDifference(yysj, startDate);

                //获取当前时间节点前未达标年份 
                List<String> lists = commissionService.getWdbnf(htSno, startTime);
                //每有一年未达标 去掉该年的投资分摊
                for (String item : lists) {
                    if (item != null && !"".equals(item)) {
                        monthnum1 = monthnum1 - 12;
                    }
                }
                BigDecimal tzft_st = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum1)).toString());

                BigDecimal jjjs_st = dk_st.subtract(tzft_st).subtract(nf_st).subtract(dj_st).subtract(zscp_st);


                //结束时间的累计计奖基数 去除未达标的年份数据
                BigDecimal dk_end = new BigDecimal("12606078.11");                                                                                        //累计截止至结束时间 去除未达标年份数据 待解决 U8客户明细账
                BigDecimal nf_end = new BigDecimal(htExcelDataList.get(0).getNf2021() == null ? "0" : htExcelDataList.get(0).getNf2021().toString());         //累计截止至结束时间 去除未达标年份数据 年服 excel取数
                BigDecimal dj_end  = new BigDecimal(htExcelDataList.get(0).getDj2021() == null ? "0" : htExcelDataList.get(0).getDj2021().toString());        //累计截止至结束时间  去除未达标年份数据 定金 excel取数
                BigDecimal zscp_end  = new BigDecimal(htExcelDataList.get(0).getZscp2021() == null ? "0" : htExcelDataList.get(0).getZscp2021().toString());   //累计截止至结束时间 去除未达标年份数据 赠送产品 excel取数

                LocalDate endDate = sdf.parse(endTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //相差月数
                int monthnum2 = TimeUtil.calculateMonthDifference(yysj, endDate);

                //获取当前时间节点前未达标年份 
                List<String> stringList = commissionService.getWdbnf(htSno, endTime);
                //每有一年未达标 去掉该年的投资分摊
                for (String item : stringList) {
                    if (item != null && !"".equals(item)) {
                        monthnum2 = monthnum2 - 12;
                    }
                }
                BigDecimal tzft_end = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum2)).toString());

                BigDecimal jjjs_end = dk_end.subtract(tzft_end).subtract(nf_end).subtract(dj_end).subtract(zscp_end);

                //前5年对应季度应发的预留30%
                jjjs5s= ( jjjs_end.subtract(jjjs_st) ) .multiply( new BigDecimal("0.3" ) );
                log.info("=============前5年剩余30%的计奖基数：" + jjjs5s + "===================");
                 */
            }

        }
        map.put("jjjs5s",jjjs5s);
        return map;

    }

    /**
     * 大型EMC预留30%奖励
     * 前5年利润率计算 (去除未达标的年份数据)
     * @param
     * @return
     */
    public Map<String, Object> getEmcAndDkLrl5s(List<HtExcelData> htExcelDataList,String htsno,String year,String jd) throws ParseException {
        // 计算前5年净利润率  计5年的数据
        BigDecimal sr5s = new BigDecimal(getU8Info(htsno,year,jd,3).get("sr"));                             //5年前-当期季度 前5年收入(不累计) 待解决 U8/600105贷
        BigDecimal nhcb5s = new BigDecimal(getU8Info(htsno,year,jd,3).get("cb"));                           //5年前-当期季度 前5年能耗成本(不累计) 待解决 U8/520101贷
        BigDecimal fy5s = new BigDecimal(getU8Info(htsno,year,jd,3).get("fy"));                                  //5年前-当期季度 前5年费用(不累计) 待解决 U8/520102借-52010212借

        int monthnum=12*5;
        //获取未达标年份 
        List<HtDb> htDbList = commissionService.getUnDbList (htsno);
        if(htDbList.size()>0){
            //每有一年未达标 去掉该年的投资分摊
            for (HtDb db : htDbList) {
                monthnum = monthnum - 12;
            }
        }
        //前5年设备分摊额=设备分摊(月)* 12 * 5  excel导入
        BigDecimal sbft5s = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum)).toString());

        //前5年净利润率
        double lrl5s = (((sr5s.subtract(nhcb5s).subtract(sbft5s).subtract(fy5s)).multiply(new BigDecimal(0.9892)).multiply(new BigDecimal(0.85)))
                .divide(sr5s, 4, BigDecimal.ROUND_HALF_UP)).doubleValue();

        Map<String, Object> map = new HashMap<>();
        map.put("lrl5s", lrl5s);         //前5年的净利润率

        return map;
    }








}



    
    




