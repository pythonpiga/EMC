package com.broad.emc.module.controller;

import com.broad.emc.common.annotation.MyArchivesLog;

import com.broad.emc.module.entity.HtExcelData;
import com.broad.emc.module.entity.HtTcjg;
import com.broad.emc.module.service.CommissionService;
import com.broad.emc.module.service.ContractManageService;
import com.broad.emc.module.service.InfoService;
import com.broad.emc.module.vo.HtxxVo;
import com.broad.emc.module.vo.HtywyVo;
import com.broad.emc.module.vo.ReturnData;
import lombok.extern.slf4j.Slf4j;
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

    ;

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
            int result = commissionService.updateTcry(htywyVo);
            if (result > 0) {
                ret.setMsg("已修改提成人员信息");
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
        if (htExcelDataList == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try {
            for (HtExcelData htExcelData : htExcelDataList) {
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
        if (htTcjg == null) {
            return ReturnData.getFailData("缺少参数");
        }
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
     * 无奖励年份记录
     *
     * @param flag true 新增无奖励年份  false 清除已记录的无奖励年份
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void noRewardRecord(String htsno, String year, String jd, Boolean flag) throws ParseException {
        //获得当期的上一年年份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date dt = sdf.parse(year);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR, -1);// 日期减1年
        Date dt1 = rightNow.getTime();
        String LastYear = sdf.format(dt1);

        //当前时间
        String curmonth = "1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "";
        String time = year + "-" + curmonth;

        HtTcjg htTcjg = new HtTcjg();
        htTcjg.setHtSno(htsno);
        htTcjg.setYear(year);
        htTcjg.setTcry("");
        htTcjg.setWdbnf(flag == true ? LastYear : "");
        htTcjg.setJd(flag == true ? jd : "");
        htTcjg.setTime(time);

        List<HtTcjg> tcjgInfo = commissionService.getTcjg(htTcjg);
        if (tcjgInfo.size() == 0) {
            //新增 记录未达标年份
            commissionService.saveTcjg(htTcjg);
        } else {
            //更新 记录未达标年份
            commissionService.updateTcjg(htTcjg);
        }

    }
    

    /**
     * 计算提成 大型EMC
     * admin权限
     * @MyArchivesLog 操作日志记录
     * @param year 年份 lx 奖励类型  jd  季度
     * @return
     */
    @RequestMapping("/EmcCommission")
    @RequiresRoles("admin")
    @MyArchivesLog(operteContent="计算提成")
    public ReturnData EmcCommission(@RequestParam String year, @RequestParam String jd) {
        ReturnData ret = ReturnData.getSuccessData();
        List<HtxxVo> list=new ArrayList<>();
        try {
            BigDecimal jjjs = null;        //当期累计计奖基数
            BigDecimal jjjs5s = null;      //前5年当季计奖基数的30% 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //1. 获取所有合同信息
            List<HtxxVo> htxxVoList = contractManageService.getContractList1(null);
            for (HtxxVo htxxVo : htxxVoList) {
                
                //大型emc的签订时间在2020.01.01
                if(htxxVo.getQdrq().after( sdf.parse("2020-01-01") )){
                    continue; //跳出，下一个合同
                }

                //过滤掉已经当前时间已计算过的合同
                HtTcjg tcjg =new HtTcjg();
                tcjg.setHtSno(htxxVo.getHtsno());
                tcjg.setYear(year);
                tcjg.setJd(jd);

                List<HtTcjg> tcjgList = commissionService.getTcjg(tcjg);
                if(tcjgList.size()>0){
                    log.info("+++++++++++++++++++++++" + htxxVo.getHtmc() + ",htsno==" + htxxVo.getHtsno()+"已计算过"+year+"第"+jd+"季度的奖励");
                    continue; //跳出，下一个合同
                }
                
                log.info("+++++++++++++++++++++++" + htxxVo.getHtmc() + ",htsno==" + htxxVo.getHtsno() + "开始计算大型EMC奖励,第" + year + "年,第" + jd + "季度，能耗合同标志：" + htxxVo.getSfnhzbht() + "++++++++++++++++++++++++++++++++++++++++++");
                
                //2.计算净利润率 去年and当期
                //去年净利润率 说明：不累计,算 上年1.1-上年12.31
                //查询导入的excel  前期手工 后期自动取数
                List<HtExcelData> htExcelDataList = commissionService.getHtExcelData(htxxVo.getHtsno(),year);
                if (htExcelDataList.size() == 0) {
                    log.info("=============该合同：" + htxxVo.getHtsno() + "无导入excel数据，无法计算，结束===================");
                    continue;   //进入下次合同计算
                }
                //获的上年跟当前的净利润率
                Map<String, Object> lrlInfo = getEmcAndDkLrl(htExcelDataList, htxxVo, jd);
                double LastLrl = (double) lrlInfo.get("LastLrl");
                double Lrl = (double) lrlInfo.get("Lrl");
                
                //3. 净利润率是否达标 能耗合同/非能耗合同
                if ("0".equals(htxxVo.getSfnhzbht())) {       
                    //3.1.1  非能耗 上年净利润率需要≥0.08
                    if (LastLrl < 0.08) {
                        //无奖励的年份要记录 计算计奖基数时该年的数据不能算入到款/年服等
                        noRewardRecord(htxxVo.getHtsno(), year, jd, true);
                        //若是第四季度且当年净利润率达标8% 则给予奖励 否则无奖励 跳过此合同
                        if (!"4".equals(jd) || Lrl < 0.08) {
                            log.info("=============上年非能耗合同净利润率：" + LastLrl + "<0.08,无奖励，结束===================");
                            continue;   //进入下次合同计算
                        }
                    }
                    //3.1.2 当期净利润率需要>0.08
                    if (Lrl <= 0.08) {
                        log.info("=============当期非能耗合同净利润率：" + Lrl + "<=0.08,无奖励，结束===================");
                        continue;   //进入下次合同计算
                    }
                } else {      // 能耗总包  
                    //3.2.1 上年净利润率能耗总包需要≥0.05
                    if (LastLrl < 0.05) {
                        //无奖励的年份要记录 计算计奖基数时该年的数据不能算入到款/年服等
                        noRewardRecord(htxxVo.getHtsno(), year, jd, true);
                        //若是第四季度且当年净利润率达标5% 则给予奖励 否则无奖励 跳过此合同
                        if (!"4".equals(jd) || Lrl < 0.05) {
                            log.info("=============上年能耗总包项目净利润率：" + LastLrl + "<0.05,无奖励，结束===================");
                            continue;   //进入下次合同计算
                        }
                    }
                    //3.2.2 当期净利润率需要>0.05
                    if (Lrl <= 0.05) {
                        log.info("=============当期能耗总包项目净利润率：" + Lrl + "<=0.05,无奖励，结束计算===================");
                        continue;   //进入下次合同计算
                    }
                }
                //净利润率达标
                log.info("====================净利润达标，继续=========================");
                
                

                //4. 运营款是否收齐  手工excel导入
                if ("是".equals(htExcelDataList.get(0).getYyksqbz())) {
                    
                    //5. 计算计奖基数
                    Map<String, Object> jjjsInfo = getEmcJjjs(htExcelDataList, htxxVo, year, jd);
                    jjjs = (BigDecimal) jjjsInfo.get("jjjs");

                    //6. 判断能耗/非能耗项目
                    if ("0".equals(htxxVo.getSfnhzbht())) {         // 非能耗合同 按100%计算
                        jjjs = jjjs.multiply(new BigDecimal("1"));
                    } else {  //能耗项目 重难点 2021.12.31之前 计奖基数*100%  2021.12.31之后 计奖基数*70%
                        
                        //6.1 计算能耗总包计奖基数
                        //时间判断 当前时间是否在2022.1.1之后 说明：2022.1.1之前 计奖基数100%  之后 计奖基数70%
                        String curtime=year+"-"+("1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "");
                        BigDecimal jjjs1 = null;      //运营时间-2021.12.31之前的计奖基数 计100%
                        BigDecimal jjjs2 = null;      //2021.12.31-当前时间的计奖基数 计70%
                        
                        //2020.1.1之后  分两部分  (2022.1.1累计计奖基数的100%)+（2022.1.1-当前时间的计奖基数的70%）
                        if(sdf.parse(curtime).after(sdf.parse("2021-12-31"))){
                            //6.1.1 计奖基数1 2021.12.31累计计奖基数的100%  注意：2021.12.31前 不需要剔除净利润率不达标的年份数据
                            BigDecimal dk2021 = new BigDecimal("12606078.11");                                                                                     //累计2021.12.31到款(累计到季) 待解决 U8客户明细账
                            BigDecimal nf2021 = new BigDecimal(htExcelDataList.get(0).getNf2021() == null ? "0" : htExcelDataList.get(0).getNf2021().toString());         //累计2021.12.31年服 excel取数
                            BigDecimal dj2021 = new BigDecimal(htExcelDataList.get(0).getDj2021() == null ? "0" : htExcelDataList.get(0).getDj2021().toString());        //累计2021.12.31定金 excel取数
                            BigDecimal zscp2021 = new BigDecimal(htExcelDataList.get(0).getZscp2021() == null ? "0" : htExcelDataList.get(0).getZscp2021().toString());   //累计2021.12.31赠送产品 excel取数

                            //相差月数 运营时间-2021.12.31
                            int num = TimeUtil.calculateMonthDifference((LocalDate) jjjsInfo.get("yysj"), sdf.parse("2021-12-31").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            //当期累计投资分摊(累计到季)=(当前季度日期-运营起始日期)的月份*设备分摊(月)   excel取数
                            BigDecimal tzft2021 = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(num)).toString());

                            //计奖基数1  运营时间-2021.12.31之前 累计计奖基数 计100%
                            jjjs1 = dk2021.subtract(tzft2021).subtract(nf2021).subtract(dj2021).subtract(zscp2021);
                            
                            //6.1.2  计奖基数2 (当前季度累计计奖基数-2022.1.1的累计计基）*70%  当前季度累计计奖基数jjjs已剔除过未达标的年份数据
                            jjjs2 = (jjjs.subtract(jjjs1)).multiply(new BigDecimal(0.7));

                            // 能耗总包应发放的计奖基数
                            jjjs = jjjs1.add(jjjs2);
                            
                        }else{          //2022.1.1之前 计算100%
                            jjjs=jjjs.multiply(new BigDecimal("1"));
                        }
                        
                        //6.2 计算前5年是否发放剩余的30%
                        Map<String, Object> emcJjjs5s= getEmcJjjs5s(htExcelDataList, (LocalDate) jjjsInfo.get("curtime"), year, jd,htxxVo.getYyqsrq(),htxxVo.getHtsno());
                        jjjs5s= (BigDecimal) emcJjjs5s.get("jjjs5s");
                        
                    }

                    //7 运营时间>5年/续签项目 满足其一 
                    Long times = null;
                    try {
                        //运营起始时间
                        Date star = sdf.parse(htxxVo.getYyqsrq());
                        Date endDay = sdf.parse(jjjsInfo.get("curtime").toString());
                        Long starTime = star.getTime();
                        Long endTime = endDay.getTime();
                        times = endTime - starTime;//时间戳相差的毫秒数
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //运营时间>5 || 续签
                    if (times / 24 / 60 / 60 / 1000 > 5 * 365 || "续签".equals(htxxVo.getXqhtbz())) {
                        log.info("=============当前项目运营时间大于5年/为续签合同,继续===================");
                        jjjs = jjjs.multiply(new BigDecimal(0.5));
                    }

                    //根据合同序号htsno查找提成人员信息
                    List<HtywyVo> ywyList = commissionService.getTcry(htxxVo.getHtsno());
                    if(ywyList.size()>0){
                        //返回参与合同计算的合同信息
                        list.add(htxxVo);
                        ret.setData(list);
                    }


                    //8. 获取该合同的提成人员及比例   手工导入excel提成人员
                    String result=saveTcxx(htxxVo, year, jd,lrlInfo, jjjsInfo, Lrl, jjjs, jjjs5s);

                    if("fail".equals(result)){
                        ret.setMsg("结果保存失败");
                        return ret;
                    }
                    
                    
                    
                    //获取该合同的提成人员及比例 源代码
//                    //根据合同序号htsno查找提成人员信息
//                    List<HtywyVo> ywyList = commissionService.getTcry(htxxVo.getHtsno());
//                    if (ywyList.size() == 0) {
//                        log.info("=============该合同：" + htxxVo.getHtsno() + "无提成人员，结束===================");
//                        continue;   //进入下次合同计算
//                    }
//                    String curmonth = "1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "";
//                    String time = year + "-" + curmonth;
//                    //计算每位提成人员的奖励
//                    for (HtywyVo htywyVo : ywyList) {
//                        //8.1 将所有计算结果存到数据库
//                        HtTcjg htTcjg = new HtTcjg();
//                        htTcjg.setHtSno(htxxVo.getHtsno());
//                        htTcjg.setYear(year);
//                        htTcjg.setJd(jd);
//                        htTcjg.setSr((BigDecimal) lrlInfo.get("sr"));                   
//                        htTcjg.setCb((BigDecimal) lrlInfo.get("nhcb"));             
//                        htTcjg.setSbft((BigDecimal) lrlInfo.get("sbft"));
//                        htTcjg.setFy((BigDecimal) lrlInfo.get("fy"));
//                        htTcjg.setNf((BigDecimal) jjjsInfo.get("nf"));
//                        htTcjg.setDk((BigDecimal) jjjsInfo.get("dk"));
//                        htTcjg.setDj((BigDecimal) jjjsInfo.get("dj"));
//                        htTcjg.setZscp((BigDecimal) jjjsInfo.get("zscp"));
//                        htTcjg.setJlrl(new BigDecimal(Lrl).setScale(4, BigDecimal.ROUND_DOWN));
//                        htTcjg.setTcry(htywyVo.getKhjl());
//                        htTcjg.setTcje5s("0".equals(htxxVo.getSfnhzbht()) ? null : jjjs5s == null ? jjjs5s : jjjs5s.setScale(4, BigDecimal.ROUND_DOWN));
//                        htTcjg.setTime(time);
//
//                        BigDecimal tcje;    //提成金额
//                        if ("签单客户经理".equals(htywyVo.getRylb())) {   //客户经理
//                            if ("0".equals(htxxVo.getSfnhzbht())) {   //非能耗
//                                if ("".equals(htywyVo.getTcbl())) {
//                                    tcje = jjjs.multiply(new BigDecimal(0.01));
//                                } else {
//                                    tcje = jjjs.multiply(new BigDecimal(htywyVo.getTcbl())).multiply(new BigDecimal(0.01));
//                                }
//                            } else {  //能耗
//                                if ("".equals(htywyVo.getTcbl())) {
//                                    tcje = jjjs.multiply(new BigDecimal(0.007));
//                                } else {
//                                    tcje = jjjs.multiply(new BigDecimal(htywyVo.getTcbl())).multiply(new BigDecimal(0.007));
//                                }
//                            }
//                            //人员类型
//                            htTcjg.setRylx("签单客户经理");
//                            //提成比例
//                            htTcjg.setTcbl("".equals(htywyVo.getTcbl()) ? null : new BigDecimal(htywyVo.getTcbl()));
//                            //提成金额
//                            htTcjg.setTcje(tcje.setScale(4, BigDecimal.ROUND_DOWN));
//
//                        } else {  //主管
//                            if ("0".equals(htxxVo.getSfnhzbht())) {   //非能耗
//                                if ("".equals(htywyVo.getTcbl())) {
//                                    tcje = jjjs.multiply(new BigDecimal(0.002));
//                                } else {
//                                    tcje = jjjs.multiply(new BigDecimal(htywyVo.getTcbl())).multiply(new BigDecimal(0.002));
//                                }
//                            } else {  //能耗
//                                if ("".equals(htywyVo.getTcbl())) {
//                                    tcje = jjjs.multiply(new BigDecimal(0.0014));
//                                } else {
//                                    tcje = jjjs.multiply(new BigDecimal(htywyVo.getTcbl())).multiply(new BigDecimal(0.0014));
//                                }
//                            }
//                            //人员类型
//                            htTcjg.setRylx("主管");
//                            //提成比例
//                            htTcjg.setTcbl("".equals(htywyVo.getTcbl()) ? null : new BigDecimal(htywyVo.getTcbl()));
//                            //提成金额
//                            htTcjg.setTcje(tcje.setScale(4, BigDecimal.ROUND_DOWN));
//
//                        }
//                        
//                        //8.2 查询该合同是否已经计算过提成奖励  是 更新  否 新增
//                        HtTcjg tcjgInfo = commissionService.getTcjgInfo(htTcjg);
//                        if (tcjgInfo == null) {
//                            //未计算提成  新增
//                            try {
//                                int res = commissionService.saveTcjg(htTcjg);
//                                if (res > 0) {
//                                    log.info("==============计算完成，保存成功==========");
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                ret.setMsg("结果保存失败");
//                                log.info("=============该合同：" + htxxVo.getHtsno() + "保存到数据库失败，结束===================");
//                                return ret;
//                            }
//                        } else {
//                            //已计算提成 更新++
//                            try {
//                                int res = commissionService.updateTcjg(htTcjg);
//                                if (res > 0) {
//                                    log.info("==============计算完成，更新成功==========");
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                ret.setMsg("结果更新保存失败");
//                                log.info("=============该合同：" + htxxVo.getHtsno() + "更新数据库失败，结束===================");
//                                return ret;
//                            }
//                        }
//                    }
                
                
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
     *
     * @param year 年份 lx 奖励类型  jd  季度
     * @return
     */
    @RequestMapping("/DkCommission")
    public ReturnData DkCommission(@RequestParam String year, @RequestParam String lx, @RequestParam String jd) {
        ReturnData ret = ReturnData.getSuccessData();
        List<HtxxVo> list=new ArrayList<>();

        try {
            BigDecimal jjjs5s = null;      //前5年计奖基数的30%
            BigDecimal jjjs = null;        //当期计奖基数
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //到款奖励 时间在2020.1.1-至今
            //1. 获取所有合同信息
            List<HtxxVo> htxxVoList = contractManageService.getContractList1(null);
            for (HtxxVo htxxVo : htxxVoList) {
               
                //到款的签订时间在2020.01.01
                if(htxxVo.getQdrq().before( sdf.parse("2020-01-01") )){
                    continue; //跳出，下一个合同
                }

                //过滤掉已经当前时间已计算过的合同
                HtTcjg tcjg =new HtTcjg();
                tcjg.setHtSno(htxxVo.getHtsno());
                tcjg.setYear(year);
                tcjg.setJd(jd);

                List<HtTcjg> tcjgList = commissionService.getTcjg(tcjg);
                if(tcjgList.size()>0){
                    log.info("+++++++++++++++++++++++" + htxxVo.getHtmc() + ",htsno==" + htxxVo.getHtsno()+"已计算过"+year+"第"+jd+"季度的奖励");
                    continue; //跳出，下一个合同
                }
                
                log.info("+++++++++++++++++++++++当前合同：" + htxxVo.getHtmc() + ",htsno==" + htxxVo.getHtsno() + "开始计算到款奖励,第" + year + "年,第" + jd + "季度，能耗合同标志：" + htxxVo.getSfnhzbht() + "++++++++++++++++++++++++++++++++++++++++++");
                
                //2.计算净利润率 
                //2.1 去年净利润率 说明：不累计,上年1.1-上年12.31
                List<HtExcelData> htExcelDataList = commissionService.getHtExcelData(htxxVo.getHtsno(),year);
                //查询导入的excel  前期手工 后期自动取数
                if (htExcelDataList.size() == 0) {
                    log.info("=============该合同：" + htxxVo.getHtsno() + "无导入excel数据，无法计算，结束===================");
                    continue;   //进入下次合同计算
                }

                //获的上年跟当前的净利润率
                Map<String, Object> lrlInfo = getEmcAndDkLrl(htExcelDataList, htxxVo, jd);
                double LastLrl = (double) lrlInfo.get("LastLrl");
                double Lrl = (double) lrlInfo.get("Lrl");

                //3. 判断合同 能耗合同/非能耗合同
                if ("0".equals(htxxVo.getSfnhzbht())) {        //非能耗
                    //3.1.1 上年净利润率需要≥0.08
                    if (LastLrl < 0.08) {
                        //无奖励的年份要记录 计算计奖基数时该年的数据不能算入到款/年服等
                        noRewardRecord(htxxVo.getHtsno(), year, jd, true);
                        //若是第四季度且当年净利润率达标8% 则给予奖励 否则无奖励 跳过此合同
                        if (!"4".equals(jd) || Lrl < 0.08) {
                            log.info("=============上年非能耗合同净利润率：" + LastLrl + "<0.08,无奖励，结束===================");
                            continue;   //进入下次合同计算
                        }
                       
                    }
                    //3.1.2 当期净利润率需要>0.08
                    if (Lrl <= 0.08) {
                        log.info("=============当期非能耗合同净利润率：" + Lrl + "<=0.08,无奖励，结束===================");
                        continue;   //进入下次合同计算
                    }
                } else {      //能耗总包
                    //3.2.1 上年净利润率能耗总包需要≥0.05
                    if (LastLrl < 0.05) {
                        //无奖励的年份要记录 计算计奖基数时该年的数据不能算入到款/年服等
                        noRewardRecord(htxxVo.getHtsno(), year, jd, true);
                        //若是第四季度且当年净利润率达标5% 则给予奖励 否则无奖励 跳过此合同
                        if (!"4".equals(jd) || Lrl < 0.05) {
                            log.info("=============上年能耗总包项目净利润率：" + LastLrl + "<0.05,无奖励，结束===================");
                            continue;   //进入下次合同计算
                        }
                    }
                    //3.2.2 当期净利润率需要>0.05
                    if (Lrl <= 0.05) {
                        log.info("=============当期能耗总包项目净利润率：" + Lrl + "<=0.05,无奖励，结束计算===================");
                        continue;   //进入下次合同计算
                    }
                }

                //净利润率达标
                log.info("====================净利润达标，继续=========================");

                //4. 运营款是否收齐           手工excel导入
                if ("是".equals(htExcelDataList.get(0).getYyksqbz())) {
                    //5. 计算计奖基数
                    Map<String, Object> jjjsInfo = getDkJjjs(htExcelDataList, htxxVo, year, jd);
                    jjjs = (BigDecimal) jjjsInfo.get("jjjs");

                    //6. 判断能耗/非能耗项目 
                    if ("0".equals(htxxVo.getSfnhzbht())) {         // 非能耗合同 按100%计算
                        jjjs = jjjs.multiply(new BigDecimal("1"));
                    } else {  //能耗项目 能耗项目*70%
                        //6.1 计算能耗总包 项目的计奖基数
                        jjjs = jjjs.multiply(new BigDecimal("0.7"));
                        
                        //判断是否能发放预留的30%  仅2021.7及以后签订的合同有预留的30%
                        if(htxxVo.getQdrq().after(sdf.parse("2021-06-30"))){
                            //计算前5年预留的30%
                            Map<String,Object> dkJjjs5s= getDkJjjs5s( htExcelDataList, (LocalDate) jjjsInfo.get("curtime"), year, jd,htxxVo.getYyqsrq(),htxxVo);
                            jjjs5s= (BigDecimal) dkJjjs5s.get("jjjs5s");
                        }

                    }

                    //根据合同序号htsno查找提成人员信息
                    List<HtywyVo> ywyList = commissionService.getTcry(htxxVo.getHtsno());
                    if(ywyList.size()>0){
                        //返回参与合同计算的合同信息
                        list.add(htxxVo);
                        ret.setData(list);
                    }

                    //7. 获取该合同的提成人员及比例   手工导入excel提成人员
                    String result=saveTcxx(htxxVo, year, jd,lrlInfo, jjjsInfo, Lrl, jjjs, jjjs5s);
                    
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

    
    
//    /**
//     * 获取U8信息 开始时间 截止时间  未达标年份
//     */
//    public void getU8Info(String lx,String starttime,String endtime,String year){
//        commissionService.getU8Info(lx,starttime,endtime);
//    }

    /**
     * 大型EMC、到款
     * 净利润率
     * @param
     * @return
     */
    public Map<String, Object> getEmcAndDkLrl(List<HtExcelData> htExcelDataList, HtxxVo htxxVo, String jd) {

        //1.计算去年净利润率 注意：取数从去年1.1-去年12.31
        BigDecimal LastSr = new BigDecimal("3082568.8");                             //去年一年收入(不累计 从去年1.1-去年12.31) 待解决 U8/600105贷
        BigDecimal LastNhcb = new BigDecimal("1504923.8");                           //去年一年能耗成本(不累计) 待解决 U8/520101贷
        BigDecimal LastFy = new BigDecimal("1000");                                //去年一年费用(不累计) 待解决 U8/520102借-52010212借
        //去年总设备分摊额=设备分摊(月)*12  excel导入 
        BigDecimal LastSbft = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(12)).toString());

        //2.计算当期净利润率 注意：取数 从当年1.1-当季度
        BigDecimal sr = new BigDecimal("1475934.66");                                 //当期季度收入(不累计,算当年1.1-本季度截止) 待解决 U8/600105贷
        BigDecimal nhcb = new BigDecimal("1102623.27");                               //当期季度能耗成本(不累计) 待解决 U8/520101贷
        BigDecimal fy = new BigDecimal("10000");                                     //当期季度费用(不累计) 待解决 U8/520102借-52010212借
        //当期设备分摊额=设备分摊(月)* jd * 3  excel导入
        BigDecimal sbft = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(jd)).multiply(new BigDecimal(3)).toString());

        double LastLrl;
        double Lrl;

        if ("0".equals(htxxVo.getSfnhzbht())) {        // 非能耗  （1-6%*12%）*85%
            
            LastLrl = (((LastSr.subtract(LastNhcb).subtract(LastSbft).subtract(LastFy)).multiply(new BigDecimal(0.9928)).multiply(new BigDecimal(0.85)))
                    .divide(LastSr, 4, BigDecimal.ROUND_HALF_UP)).doubleValue();
            Lrl = (((sr.subtract(nhcb).subtract(sbft).subtract(fy)).multiply(new BigDecimal(0.9928)).multiply(new BigDecimal(0.85)))
                    .divide(sr, 4, BigDecimal.ROUND_HALF_UP)).doubleValue();

        } else {      // 能耗总包  （1-9%*12%）*85%）
            LastLrl = (((LastSr.subtract(LastNhcb).subtract(LastSbft).subtract(LastFy)).multiply(new BigDecimal(0.9892)).multiply(new BigDecimal(0.85)))
                    .divide(LastSr, 4, BigDecimal.ROUND_HALF_UP)).doubleValue();
            Lrl = (((sr.subtract(nhcb).subtract(sbft).subtract(fy)).multiply(new BigDecimal(0.9892)).multiply(new BigDecimal(0.85)))
                    .divide(sr, 4, BigDecimal.ROUND_HALF_UP)).doubleValue();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("LastLrl", LastLrl);
        map.put("Lrl", Lrl);
        map.put("sr", sr);           //当期收入 不累计
        map.put("nhcb", nhcb);       //当期能耗成本
        map.put("fy", fy);           //当期费用
        map.put("sbft", sbft);       //当期设备分摊

        return map;


    }


    /**
     * 大型EMC  
     * 前5年利润率
     * @param
     * @return
     */
    public Map<String, Object> getEmcAndDkLrl5s(List<HtExcelData> htExcelDataList) throws ParseException {
        //计算前5年净利润率  计5年的数据
        BigDecimal sr5s = new BigDecimal("3082568.8");                             //前5年收入(不累计) 待解决 U8/600105贷
        BigDecimal nhcb5s = new BigDecimal("1504923.8");                           //前5年能耗成本(不累计) 待解决 U8/520101贷
        BigDecimal fy5s = new BigDecimal("1000");                                  //前5年费用(不累计) 待解决 U8/520102借-52010212借
        //前5年设备分摊额=设备分摊(月)* 12 * 5  excel导入
        BigDecimal sbft5s = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(12)).multiply(new BigDecimal(5)).toString());

        //前5年净利润率
        double lrl5s = (((sr5s.subtract(nhcb5s).subtract(sbft5s).subtract(fy5s)).multiply(new BigDecimal(0.9892)).multiply(new BigDecimal(0.85)))
                .divide(sr5s, 4, BigDecimal.ROUND_HALF_UP)).doubleValue();


        Map<String, Object> map = new HashMap<>();
        map.put("lrl5s", lrl5s);         //前5年的净利润率

        return map;
    }


    /**
     * 大型EMC
     * 计奖基数
     * ps:去除未达标的年份数据
     * @param
     * @return
     */
    public Map<String, Object> getEmcJjjs(List<HtExcelData> htExcelDataList, HtxxVo htxxVo, String year, String jd) throws ParseException {
        //1.计奖基数的数据 计算计奖基数=到款-投资分摊-年服-定金-赠送产品       注意：取累计 运营时间开始-当前季度截止 去除未达标的年份数据
        BigDecimal dk = new BigDecimal("12606078.11");                                                                                //当期累计到款(累计 运营时间开始-当前季度截止 去除未达标的年份数据) 待解决 U8客户明细账
        BigDecimal nf = new BigDecimal(htExcelDataList.get(0).getNf() == null ? "0" : htExcelDataList.get(0).getNf().toString());           //当期累计年服(累计到季，可取当年) excel取数
        BigDecimal dj = new BigDecimal(htExcelDataList.get(0).getDj() == null ? "0" : htExcelDataList.get(0).getDj().toString());           //当期累计定金(累计到季，可取当年) excel取数
        BigDecimal zscp = new BigDecimal(htExcelDataList.get(0).getZscp() == null ? "0" : htExcelDataList.get(0).getZscp().toString());       //当期累计赠送产品(累计到季，可取当年) excel取数

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //运营时间
        LocalDate yysj = sdf.parse(htxxVo.getYyqsrq()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //当前季度时间
        String curmonth = "1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "";
        LocalDate curtime = sdf.parse(year + "-" + curmonth).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //相差月数
        int monthnum = TimeUtil.calculateMonthDifference(yysj, curtime);

        //当期累计投资分摊(累计到季)=(当前季度日期-运营起始日期)的月份*设备分摊(月)   excel取数 但是要去除不达标的年份数据
        String time = year + "-" + curmonth;
        //获取未达标年份 
        List<String> lists = commissionService.getWdbnf(htxxVo.getHtsno(), time);
        //每有一年未达标 去掉该年的投资分摊
        for (String item : lists) {
            if (item != null && !"".equals(item)) {
                monthnum = monthnum - 12;
            }
        }
        BigDecimal tzft = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum)).toString());

        //计奖基数
        BigDecimal jjjs = dk.subtract(tzft).subtract(nf).subtract(dj).subtract(zscp);

        Map<String, Object> map = new HashMap<>();
        map.put("jjjs", jjjs);
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
     * 大型EMC
     * 前5年预留30%计奖基数
     * ps:去除未达标的年份数据
     * @param
     * @return
     */
    public Map<String, Object> getEmcJjjs5s(List<HtExcelData> htExcelDataList,LocalDate curtime,String year,String jd,String yyrq,String htSno) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Map<String ,Object> map=new HashMap<>();
        BigDecimal jjjs5s=new BigDecimal( "0" );
        
        //时间判断 2021.12.31-当前季度时间 是否有5年 否则不用计算
        int num = TimeUtil.calculateMonthDifference(sdf.parse("2021-12-31").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), curtime);
        if (num >= 5 * 12) {

            // 计算前5年的净利润率
            Map<String, Object> lrl5sInfo= getEmcAndDkLrl5s(htExcelDataList);
            if ((double) lrl5sInfo.get("lrl5s") >= 0.08) {
                log.info("=============当期能耗合同前5年净利润率：" + lrl5sInfo.get("lrl5s") + ">=0.08,开始计算前5年预留的30%奖励===================");

                //ex: 计算2028第二季度预留的30%
                //计算发放前5年对应季度剩余的30%  如计算2028第二季度预留的30%，时间应该是2023.4.1 - 2023.6.30 2023年的第二季度
                
                //5年前的当前季度 2023 第二季度
                String endTime=(Integer.parseInt(year) - 5)+"-"+("1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "");

                //5年前的当前季度的前一个季度 2023 第一季度
                String startTime=("1".equals(jd) ? (Integer.parseInt(year) - 6) : (Integer.parseInt(year) - 5))+"-"+
                        ("1".equals(jd) ? "12-31" : "2".equals(jd) ? "03-31" : "3".equals(jd) ? "06-30" : "4".equals(jd) ? "09-30" : "");
                
                //起始时间的累计计奖基数 去除未达标的年份数据
                BigDecimal dk_st = new BigDecimal("12606078.11");                                                                                        //累计截止至起始时间 去除未达标年份数据 待解决 U8客户明细账
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
                
                
            }

        }
        
        map.put("jjjs5s",jjjs5s);
        return map;
        
    }
    


   
    /**
     * 到款奖励
     * 计奖基数
     * ps:去除未达标的年份数据
     * @param
     * @return
     */
    public Map<String, Object> getDkJjjs(List<HtExcelData> htExcelDataList, HtxxVo htxxVo, String year, String jd) throws ParseException {
        //计奖基数的数据 计算计奖基数=到款(100%、50%)+水电气抵扣额(80%excel已计算)-投资分摊-年服-定金-赠送产品       注意：取累计 运营时间开始-当前季度截止 去除未达标的年份数据
        BigDecimal dk = new BigDecimal("12606078.11");                                                                                //当期累计到款(累计 运营时间开始-当前季度截止 去除未达标的年份数据) 待解决 U8客户明细账
        BigDecimal nf = new BigDecimal(htExcelDataList.get(0).getNf() == null ? "0" : htExcelDataList.get(0).getNf().toString());           //当期累计年服(累计到季，可取当年) excel取数
        BigDecimal dj = new BigDecimal(htExcelDataList.get(0).getDj() == null ? "0" : htExcelDataList.get(0).getDj().toString());           //当期累计定金(累计到季，可取当年) excel取数
        BigDecimal zscp = new BigDecimal(htExcelDataList.get(0).getZscp() == null ? "0" : htExcelDataList.get(0).getZscp().toString());       //当期累计赠送产品(累计到季，可取当年) excel取数

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //运营时间
        LocalDate yysj = sdf.parse(htxxVo.getYyqsrq()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //当前季度时间
        String curmonth = "1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "";
        LocalDate curtime = sdf.parse(year + "-" + curmonth).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //相差月数
        int monthnum = TimeUtil.calculateMonthDifference(yysj, curtime);

        //当期累计投资分摊(累计到季)=(当前季度日期-运营起始日期)的月份*设备分摊(月)   excel取数 但是要去除不达标的年份数据
        String time = year + "-" + curmonth;
        //获取未达标年份 
        List<String> lists = commissionService.getWdbnf(htxxVo.getHtsno(), time);
        //每有一年未达标 去掉该年的投资分摊
        for (String item : lists) {
            if (item != null && !"".equals(item)) {
                monthnum = monthnum - 12;
            }
        }
        BigDecimal tzft = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum)).toString());

        //签订日期在2021.7后 对运营款(到款)进行判断 到款<=1500 按50%计算 
        if (htxxVo.getQdrq().after(sdf.parse("2021-06-30"))) {
            if (dk.compareTo(new BigDecimal("15000000")) <= 0) {
                dk = dk.multiply(new BigDecimal("0.5"));
            }
        }

        //获取excel的水电气抵扣额  excel取数 已计算80%
        BigDecimal sdqdke = "" .equals(htExcelDataList.get(0).getSdqdke() )? new BigDecimal("0") :  htExcelDataList.get(0).getSdqdke()  ;

        //计奖基数=到款+水电气抵扣额
        BigDecimal jjjs = dk.add(sdqdke).subtract(tzft).subtract(nf).subtract(dj).subtract(zscp);

        Map<String, Object> map = new HashMap<>();
        map.put("jjjs", jjjs);
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
     * 到款
     * 前5年预留30%计奖基数
     * ps:去除未达标的年份数据
     * @param
     * @return
     */
    public Map<String, Object> getDkJjjs5s(List<HtExcelData> htExcelDataList,LocalDate curtime,String year,String jd,String yyrq,HtxxVo htxxVo) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Map<String ,Object> map=new HashMap<>();
        BigDecimal jjjs5s=new BigDecimal( "0" );

        //时间判断 2021.7.1-当前季度时间 是否有5年 否则不用计算
        int num = TimeUtil.calculateMonthDifference(sdf.parse("2021-07-01").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), curtime);
        if (num >= 5 * 12) {
            // 计算前5年的净利润率
            Map<String, Object> lrl5sInfo= getEmcAndDkLrl5s(htExcelDataList);
            if ((double) lrl5sInfo.get("lrl5s") >= 0.08) {
                log.info("=============当期能耗合同前5年净利润率：" + lrl5sInfo.get("lrl5s") + ">=0.08,开始计算前5年预留的30%奖励===================");

                //ex: 计算2028第二季度预留的30%  时间应该是2023.4.1 - 2023.6.30 2023年的第二季度
                //计算发放前5年对应季度剩余的30%  

                //5年前的当前季度 2023 第二季度
                String endTime=(Integer.parseInt(year) - 5)+"-"+("1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "");

                //5年前的当前季度的前一个季度 2023 第一季度
                String startTime=("1".equals(jd) ? (Integer.parseInt(year) - 6) : (Integer.parseInt(year) - 5))+"-"+
                        ("1".equals(jd) ? "12-31" : "2".equals(jd) ? "03-31" : "3".equals(jd) ? "06-30" : "4".equals(jd) ? "09-30" : "");

                //1. 起始时间的累计计奖基数 去除未达标的年份数据
                BigDecimal dk_st = new BigDecimal("12606078.11");                                                                                        //累计截止至起始时间 去除未达标年份数据 待解决 U8客户明细账
                BigDecimal nf_st  = new BigDecimal(htExcelDataList.get(0).getNf2021() == null ? "0" : htExcelDataList.get(0).getNf2021().toString());         //累计截止至起始时间 去除未达标年份数据 年服 excel取数
                BigDecimal dj_st  = new BigDecimal(htExcelDataList.get(0).getDj2021() == null ? "0" : htExcelDataList.get(0).getDj2021().toString());        //累计截止至起始时间  去除未达标年份数据 定金 excel取数
                BigDecimal zscp_st  = new BigDecimal(htExcelDataList.get(0).getZscp2021() == null ? "0" : htExcelDataList.get(0).getZscp2021().toString());   //累计截止至起始时间 去除未达标年份数据 赠送产品 excel取数

                //运营时间
                LocalDate yysj = sdf.parse(yyrq).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate startDate = sdf.parse(startTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //相差月数
                int monthnum1 = TimeUtil.calculateMonthDifference(yysj, startDate);

                //获取当前时间节点前未达标年份 
                List<String> lists = commissionService.getWdbnf(htxxVo.getHtsno(), startTime);
                //每有一年未达标 去掉该年的投资分摊
                for (String item : lists) {
                    if (item != null && !"".equals(item)) {
                        monthnum1 = monthnum1 - 12;
                    }
                }
                BigDecimal tzft_st = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum1)).toString());

                //水电气抵扣额
                List<HtExcelData> list = commissionService.getHtExcelData(htxxVo.getHtsno(),startTime.substring(0,4));
                BigDecimal sdqdke_st=list.get(0).getSdqdke();
                
                //对运营款(到款)进行判断 到款<=1500 按50%计算
                if (dk_st.compareTo(new BigDecimal("15000000")) <= 0) {
                    dk_st = dk_st.multiply(new BigDecimal("0.5"));
                }
                
                BigDecimal jjjs_st = dk_st.add(sdqdke_st).subtract(tzft_st).subtract(nf_st).subtract(dj_st).subtract(zscp_st);


                //2. 结束时间的累计计奖基数 去除未达标的年份数据
                BigDecimal dk_end = new BigDecimal("12606078.11");                                                                                        //累计截止至结束时间 去除未达标年份数据 待解决 U8客户明细账
                BigDecimal nf_end = new BigDecimal(htExcelDataList.get(0).getNf2021() == null ? "0" : htExcelDataList.get(0).getNf2021().toString());         //累计截止至结束时间 去除未达标年份数据 年服 excel取数
                BigDecimal dj_end  = new BigDecimal(htExcelDataList.get(0).getDj2021() == null ? "0" : htExcelDataList.get(0).getDj2021().toString());        //累计截止至结束时间  去除未达标年份数据 定金 excel取数
                BigDecimal zscp_end  = new BigDecimal(htExcelDataList.get(0).getZscp2021() == null ? "0" : htExcelDataList.get(0).getZscp2021().toString());   //累计截止至结束时间 去除未达标年份数据 赠送产品 excel取数

                LocalDate endDate = sdf.parse(endTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //相差月数
                int monthnum2 = TimeUtil.calculateMonthDifference(yysj, endDate);

                //获取当前时间节点前未达标年份 
                List<String> stringList = commissionService.getWdbnf(htxxVo.getHtsno(), endTime);
                //每有一年未达标 去掉该年的投资分摊
                for (String item : stringList) {
                    if (item != null && !"".equals(item)) {
                        monthnum2 = monthnum2 - 12;
                    }
                }
                BigDecimal tzft_end = new BigDecimal(htExcelDataList.get(0).getSbft() == null ? "0" : htExcelDataList.get(0).getSbft().multiply(new BigDecimal(monthnum2)).toString());

                //水电气抵扣额
                List<HtExcelData> list_end = commissionService.getHtExcelData(htxxVo.getHtsno(),endTime.substring(0,4));
                BigDecimal sdqdke_end=list_end.get(0).getSdqdke();

                //对运营款(到款)进行判断 到款<=1500 按50%计算
                if (dk_end.compareTo(new BigDecimal("15000000")) <= 0) {
                    dk_end = dk_end.multiply(new BigDecimal("0.5"));
                }
                
                BigDecimal jjjs_end = dk_end.add(sdqdke_end).subtract(tzft_end).subtract(nf_end).subtract(dj_end).subtract(zscp_end);

                //前5年对应季度应发的预留30%
                jjjs5s= ( jjjs_end.subtract(jjjs_st) ) .multiply( new BigDecimal("0.3" ) );
                log.info("=============前5年剩余30%的计奖基数：" + jjjs5s + "===================");


            }

        }

        map.put("jjjs5s",jjjs5s);
        return map;

    }


    /**
     * 保存提成信息
     * 
     */
    public String saveTcxx(HtxxVo htxxVo,String year,String jd,Map<String, Object> lrlInfo,Map<String, Object> jjjsInfo,double Lrl,BigDecimal jjjs,BigDecimal jjjs5s){
        
        //根据合同序号htsno查找提成人员信息
        List<HtywyVo> ywyList = commissionService.getTcry(htxxVo.getHtsno());
        if(ywyList.size()>0){   //无提成人员，结束
            String curmonth = "1".equals(jd) ? "03-31" : "2".equals(jd) ? "06-30" : "3".equals(jd) ? "09-30" : "4".equals(jd) ? "12-31" : "";
            String curtime = year + "-" + curmonth;

            for (HtywyVo htywyVo : ywyList) {

                // 将所有计算结果存到数据库
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
                htTcjg.setTcje5s("0".equals(htxxVo.getSfnhzbht()) ? null : jjjs5s == null ? jjjs5s : jjjs5s.setScale(4, BigDecimal.ROUND_DOWN));
                htTcjg.setTime(curtime);

                BigDecimal tcje;    //提成金额
                if ("签单客户经理".equals(htywyVo.getRylb())) {   //客户经理
                    if ("0".equals(htxxVo.getSfnhzbht())) {   //非能耗
                        if ("".equals(htywyVo.getTcbl())) {
                            tcje = jjjs.multiply(new BigDecimal(0.01));
                        } else {
                            tcje = jjjs.multiply(new BigDecimal(htywyVo.getTcbl())).multiply(new BigDecimal(0.01));
                        }
                    } else {  //能耗
                        if ("".equals(htywyVo.getTcbl())) {
                            tcje = jjjs.multiply(new BigDecimal(0.007));
                        } else {
                            tcje = jjjs.multiply(new BigDecimal(htywyVo.getTcbl())).multiply(new BigDecimal(0.007));
                        }
                    }
                    //人员类型
                    htTcjg.setRylx("签单客户经理");
                    //提成比例
                    htTcjg.setTcbl("".equals(htywyVo.getTcbl()) ? null : new BigDecimal(htywyVo.getTcbl()));
                    //提成金额
                    htTcjg.setTcje(tcje.setScale(4, BigDecimal.ROUND_DOWN));

                } else {  //主管
                    if ("0".equals(htxxVo.getSfnhzbht())) {   //非能耗
                        log.info("==" + htywyVo.getTcbl());
                        if ("".equals(htywyVo.getTcbl())) {
                            tcje = jjjs.multiply(new BigDecimal(0.002));
                        } else {
                            tcje = jjjs.multiply(new BigDecimal(htywyVo.getTcbl())).multiply(new BigDecimal(0.002));
                        }
                    } else {  //能耗
                        if ("".equals(htywyVo.getTcbl())) {
                            tcje = jjjs.multiply(new BigDecimal(0.0014));
                        } else {
                            tcje = jjjs.multiply(new BigDecimal(htywyVo.getTcbl())).multiply(new BigDecimal(0.0014));
                        }
                    }
                    //人员类型
                    htTcjg.setRylx("主管");
                    //提成比例
                    htTcjg.setTcbl("".equals(htywyVo.getTcbl()) ? null : new BigDecimal(htywyVo.getTcbl()));
                    //提成金额
                    htTcjg.setTcje(tcje.setScale(4, BigDecimal.ROUND_DOWN));

                }
                //9.1 查询该合同是否已经计算过提成奖励  是 更新  否 新增
                HtTcjg tcjgInfo = commissionService.getTcjgInfo(htTcjg);
                if (tcjgInfo == null) {
                    //未计算提成  新增
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
                    //已计算提成 更新++
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

  




}



    
    




