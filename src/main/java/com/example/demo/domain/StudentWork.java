package com.example.demo.domain;

import lombok.Data;

import java.util.Map;

/**
 * descript
 *
 * @author orange
 * @Time 2018/5/11 0011
 */
@Data
public class StudentWork {

    private String userName;

    private Long handInTime;

    private Long costTime;

    private int correctState;

    private Double score;

    private Double selfScore;

    private int checkWrongScore;

    private int wrongNum;

    private int haveSeen;

    private int emendNum;

    private int haveEmendedNum;

    private int forHelpTimes;


    public String queryCorrectState() {
        if (this.correctState == 1) {
            return "已总批";
        } else if (this.correctState == 2) {
            return "已单批";
        }
        return "未批改";
    }

    public String queryHaveSeen(){
        if (this.haveSeen == 1){
            return "已看";
        }
        return "未看";
    }

    public void jsonToObject(Map<String, Object> json){
        this.userName = (String) json.get("userName");
        this.score = (Double) json.get("score");
        this.selfScore = (Double) json.get("selfScore");
        this.costTime = (Long) json.get("costTime");
        this.handInTime = (Long) json.get("handInTime");

        long forHelpTimes = (Long) json.get("forHelpTimes");
        this.forHelpTimes = (int)forHelpTimes;

        long wrongNum = (Long) json.get("wrongNum");
        this.wrongNum = (int) wrongNum;

        long checkWrongScore= (Long) json.get("checkWrongScore");
        this.checkWrongScore = (int) checkWrongScore;

        long correctState = (Long) json.get("correctState");
        this.correctState = (int) correctState;

        long haveSeen = (Long) json.get("haveSeen");
        this.haveSeen = (int) haveSeen;

        long emendNum = (Long) json.get("emendNum");
        this.emendNum = (int) emendNum;

        long haveEmendedNum = (Long) json.get("haveEmendedNum");
        this.haveEmendedNum = (int) haveEmendedNum;
    }

}
