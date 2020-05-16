package com.example.fjob.Entity.job;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JobMessage {
    @PrimaryKey(autoGenerate = true)
   private int id;
    @ColumnInfo(name = "JobName")
    private String  jobName;
    @ColumnInfo(name = "条件1")
    private String jobConditionOne;
    @ColumnInfo(name = "条件2")
    private String jobConditionTwo;
    @ColumnInfo(name = "公司名称")
    private String cpnName;
    @ColumnInfo(name = "工资")
    private String jobPay;


    public JobMessage(String jobConditionOne, String jobConditionTwo,String jobName,String jobPay,String cpnName) {
    this.cpnName=cpnName;
    this.jobConditionOne=jobConditionOne;
    this.jobConditionTwo=jobConditionTwo;
    this.jobName=jobName;
    this.jobPay=jobPay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobConditionOne() {
        return jobConditionOne;
    }

    public void setJobConditionOne(String jobConditionOne) {
        this.jobConditionOne = jobConditionOne;
    }

    public String getJobConditionTwo() {
        return jobConditionTwo;
    }

    public void setJobConditionTwo(String jobConditionTwo) {
        this.jobConditionTwo = jobConditionTwo;
    }

    public String getCpnName() {
        return cpnName;
    }

    public void setCpnName(String cpnName) {
        this.cpnName = cpnName;
    }

    public String getJobPay() {
        return jobPay;
    }

    public void setJobPay(String jobPay) {
        this.jobPay = jobPay;
    }
}
