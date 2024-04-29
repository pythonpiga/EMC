package com.broad.emc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.broad.emc.module.entity.Htxx;
import com.broad.emc.module.service.ContractManageService;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmcApplication.class)
@WebAppConfiguration
public class Excel {
    @Resource
    private ContractManageService contractManageService;
    
    @Test
    public void handleExcel(){
        List<Htxx> htList= contractManageService.getHtmcList();
        
        // Excel 文件的路径
        String fileName = "C:\\Users\\ltk\\Desktop\\test.xlsx";

        List<List<Object>> data=readExcel(fileName,htList);

        exportExcel(data);
        
    }
    
    public static List<List<Object>> readExcel(String fileName,List<Htxx> htList){
        String htmc="";
        List<List<Object>> List_Data=new ArrayList<>();

        // 定义数据存储容器
        List<Map<Integer, String>> dataList = new ArrayList<>();
        // 构造监听器对象
        AnalysisEventListener<Map<Integer, String>> listener = new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void invoke(Map<Integer, String> rowData, AnalysisContext context) {
                dataList.add(rowData); // 将每行数据添加到容器中
            }

            @Override
            public void extra(CellExtra extra, AnalysisContext context) {
                // 处理额外的单元格信息，比如合并单元格、批注等
                // ...
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 数据解析完成后的操作，可以在这里进行业务逻辑处理
                // ...
            }
        };

        // 读取 Excel 文件
        EasyExcel.read(fileName).registerReadListener(listener).sheet(0).doRead();

        // 遍历数据
        for (Map<Integer, String> rowData : dataList) {
            // 处理每行数据
            List<String> list_cell=new ArrayList<>();
            for (Map.Entry<Integer, String> entry : rowData.entrySet()) {
                Integer columnIndex = entry.getKey(); // 列索引
                String value = entry.getValue();// 单元格数据

                //前三列数据
                if(columnIndex<3){
                    // 处理每个单元格的值
                    System.out.print(value+"\t" );
                    list_cell.add(value);
                    
                }

            }
            
            htmc="";
            for(Htxx htxx : htList){
                if( list_cell.get(0).equals( htxx.getYyxmbh()==null? htxx.getYyxmbh() : htxx.getYyxmbh().trim()  )){
                    htmc= htxx.getHtmc()==null? htxx.getHtmc() : htxx.getHtmc().trim();
                }
            }
            List<Object> ht_cell_list=new ArrayList<>();
            ht_cell_list.add(htmc);
            ht_cell_list.add(list_cell.get(0));
            ht_cell_list.add(list_cell.get(1));
            ht_cell_list.add(list_cell.get(2));

            List_Data.add(ht_cell_list);

        }
        System.out.println("======================");
        System.out.println(List_Data);
        
        return List_Data;

    }
    
    public static void exportExcel(List<List<Object>> List_Data){
        // 创建工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        //要写入的数据 list
        System.out.println(List_Data);
        // 写入数据
        int rowNum = 0;
        for (List<Object> stringList : List_Data) {
            System.out.println(stringList);
            //行数
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object s : stringList) {
                //列数
                Cell cell = ((Row) row).createCell(colNum++);
                if (s instanceof String) {
                    //字符串类型的
                    cell.setCellValue((String) s);
                } else if (s instanceof Integer) {
                    //int类型
                    cell.setCellValue((Integer) s);
                }
            }
        }

        // 保存Excel文件
        try {
            //
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\ltk\\Desktop\\output.xlsx");
            //写入数据
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭工作簿
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    
}
