package com.example.demo.file;

import com.example.demo.domain.StudentWork;
import com.example.demo.domain.StudentWorkMap;
import com.example.demo.excel.ExcelUtil;
import com.google.common.collect.Lists;
import org.json.simple.JSONValue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * descript
 *
 * @author orange
 * @Time 2018/5/11 0011
 */
public class File {

    private final  static String fileUrl = "C:\\Users\\Administrator\\Desktop\\test";
    private final static String excelUrl = "C:\\Users\\Administrator\\Desktop\\test";


    public static List<StudentWorkMap> readFile() throws IOException {
        List<StudentWorkMap> studentWorkMaps = Lists.newArrayList();

        java.io.File file = new java.io.File(fileUrl);

        java.io.File[] files = file.listFiles();
        for (java.io.File file1 : files){
            FileInputStream fileInputStream = new FileInputStream(file1);

            String studentWorkStatic= "";
            int length = 0;
            byte[] buf = new byte[1024];

            while ((length = fileInputStream.read(buf)) != -1){
                studentWorkStatic += new String(buf, 0, length);
            }
            studentWorkStatic = studentWorkStatic.replace("\n\t","");
            studentWorkStatic = studentWorkStatic.replace("\n","");

            List<StudentWork> studentWork = (List<StudentWork>) JSONValue.parse(studentWorkStatic);

            List<StudentWork> studentWorkList = Lists.newArrayList();
            for(int j = 0; j < studentWork.size(); j++){
                Map<String, Object> json = (Map<String, Object>) studentWork.get(j);
                StudentWork studentWork2 = new StudentWork();
                studentWork2.jsonToObject(json);
                studentWorkList.add(studentWork2);
            }


            studentWorkMaps.add(new StudentWorkMap(file1.getName(), studentWorkList));
        }

        return studentWorkMaps;
    }

    public static void main(String[] args) throws IOException {
        List<StudentWorkMap> studentWorks = readFile();

        for (StudentWorkMap studentWorkMap : studentWorks){
            java.io.File file = new java.io.File(excelUrl + "/" + studentWorkMap.getFileName().replace(".txt", ".xls"));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ExcelUtil.writeExcel(studentWorkMap.getStudentWorks(), fileOutputStream);
        }
    }
}
