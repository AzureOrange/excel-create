package com.example.demo.domain;

import lombok.Data;

import java.util.List;

/**
 * descript
 *
 * @author orange
 * @Time 2018/5/11 0011
 */
@Data
public class StudentWorkMap {

    private String fileName;

    private List<StudentWork> studentWorks;

    public StudentWorkMap(String fileName, List<StudentWork> studentWorks) {
        this.fileName = fileName;
        this.studentWorks = studentWorks;
    }
}
