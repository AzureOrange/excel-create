package com.example.demo.excel;

import com.example.demo.domain.StudentWork;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * descript
 *
 * @author orange
 * @Time 2018/5/11 0011
 */
public class ExcelUtil {


    public static void writeExcel(List<StudentWork> studentWorkList, FileOutputStream fileOutputStream) throws IOException {
        //1、创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //1.1、创建合并单元格对象
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);//起始行号，结束行号，起始列号，结束列号

        //1.2、头标题样式
        HSSFCellStyle style1 = createCellStyle(workbook, (short)16);

        //1.3、列标题样式
        HSSFCellStyle style2 = createCellStyle(workbook, (short)13);

        //2、创建工作表
        HSSFSheet sheet = workbook.createSheet("用户列表");
        //2.1、加载合并单元格对象
        sheet.addMergedRegion(cellRangeAddress);
        //设置默认列宽
        sheet.setDefaultColumnWidth(25);

        //3、创建行
        //3.1、创建头标题行；并且设置头标题
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        //加载单元格样式
        cell1.setCellStyle(style1);
        cell1.setCellValue("学生成绩统计");

        //3.2、创建列标题行；并且设置列标题
        HSSFRow row2 = sheet.createRow(1);
        String[] titles = {"用户名","成绩", "自批得分", "用时", "上交时间", "求助","错题","校错分","批改","查看状态","未订","未批订"};

        for(int i = 0; i < titles.length; i++){
            HSSFCell cell2 = row2.createCell(i);
            //加载单元格样式
            cell2.setCellStyle(style2);
            cell2.setCellValue(titles[i]);
        }
        //4、操作单元格；将用户列表写入excel
        if(studentWorkList != null){
            for(int j = 0; j < studentWorkList.size(); j++){
                HSSFRow row = sheet.createRow(j+2);
                HSSFCell cell11 = row.createCell(0);
                StudentWork studentWork =  studentWorkList.get(j);
                cell11.setCellValue(studentWork.getUserName());
                HSSFCell cell12 = row.createCell(1);
                cell12.setCellValue(studentWork.getScore());
                HSSFCell cell13 = row.createCell(2);
                cell13.setCellValue(studentWork.getSelfScore());
                HSSFCell cell14 = row.createCell(3);
                cell14.setCellValue(studentWork.getCostTime());
                HSSFCell cell15 = row.createCell(4);
                cell15.setCellValue(studentWork.getHandInTime());
                HSSFCell cell16 = row.createCell(5);
                cell16.setCellValue(studentWork.getForHelpTimes());
                HSSFCell cell17 = row.createCell(6);
                cell17.setCellValue(studentWork.getWrongNum());
                HSSFCell cell18 = row.createCell(7);
                cell18.setCellValue(studentWork.getCheckWrongScore());
                HSSFCell cell19 = row.createCell(8);
                cell19.setCellValue(studentWork.queryCorrectState());
                HSSFCell cell20 = row.createCell(9);
                cell20.setCellValue(studentWork.queryHaveSeen());
                HSSFCell cell21 = row.createCell(10);
                cell21.setCellValue(studentWork.getEmendNum());
                HSSFCell cell22 = row.createCell(11);
                cell22.setCellValue(studentWork.getHaveEmendedNum());

            }
        }
        //5、输出
        workbook.write(fileOutputStream);
        workbook.close();
    }

    /**
     * 创建单元格样式
     * @param workbook 工作簿
     * @param fontSize 字体大小
     * @return 单元格样式
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
        HSSFCellStyle style = workbook.createCellStyle();
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        //加载字体
        style.setFont(font);
        return style;
    }

}
