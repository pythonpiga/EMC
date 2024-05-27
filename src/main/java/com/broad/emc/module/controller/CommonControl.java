package com.broad.emc.module.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.broad.emc.module.entity.U8Info;
import com.broad.emc.module.service.ContractManageService;
import com.broad.emc.module.vo.U8ExcelVo;
import com.broad.emc.module.vo.U8InfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2024-05-15 19:38:02
 */
@Slf4j
@RestController
@RequestMapping("emc/Common")
public class CommonControl {

    @Autowired
    private ContractManageService contractManageService;

    /**
     * U8数据导出
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(@RequestParam("xmdh") String xmdh, @RequestParam("ny") String ny,HttpServletResponse response) {
        try {
            log.info("excel导出，入参：{}", xmdh,ny);
            
            String fileName="U8Info";
            
            List<U8InfoVo> u8Infos= contractManageService.getU8DataList(xmdh,ny);
            
            List<U8ExcelVo> u8ExcelVos=new ArrayList<>();
            for(U8InfoVo u8 : u8Infos){
                U8ExcelVo u8ExcelVo=new U8ExcelVo();
                
                u8ExcelVo.setHtmc(u8.getHtmc());
                u8ExcelVo.setYyxmbh(u8.getYyxmbh());
                u8ExcelVo.setXmdh(u8.getXmdh());
                u8ExcelVo.setNy(u8.getNy());
                u8ExcelVo.setSr(u8.getSr());
                u8ExcelVo.setCb(u8.getCb());
                u8ExcelVo.setFy(u8.getFy());
                u8ExcelVo.setDk(u8.getDk());

                u8ExcelVos.add(u8ExcelVo);
            }
            
            //在内存操作，写出到浏览器，从浏览器下载
            ExcelWriter writer = ExcelUtil.getWriter(true);
            //自定义标题名
            writer.addHeaderAlias("htmc", "合同名称");
            writer.addHeaderAlias("yyxmbh", "运营项目编号");
            writer.addHeaderAlias("xmdh", "U8项目代号");
            writer.addHeaderAlias("ny", "日期");
            writer.addHeaderAlias("sr", "收入");
            writer.addHeaderAlias("cb", "成本");
            writer.addHeaderAlias("fy", "费用");
            writer.addHeaderAlias("dk", "到款");
            

            //一次性写出list内的对象到excel，使用默认格式，强制输出标题
            writer.write(u8ExcelVos, true);

            //设置浏览器响应格式
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");

            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

            ServletOutputStream outputStream = response.getOutputStream();
            writer.flush(outputStream, true);

            //关闭流
            outputStream.close();
            writer.close();
        } catch (Exception e) {
            log.error("文件导出异常，{}", e);
        }


    }

}
    
