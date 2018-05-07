package com.example.cuong.alumni.DataModel;

/**
 * Created by Cuong on 4/25/2018.
 */

public class Job {

    private String id;
    private String jobName;
    private String companyName;
    private String jobInfomation;

    public Job(String id,String jobName, String companyName, String jobInfomation) {
        this.id=id;
        this.jobName = jobName;
        this.companyName = companyName;
        this.jobInfomation = jobInfomation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobInfomation() {
        return jobInfomation;
    }

    public void setJobInfomation(String jobInfomation) {
        this.jobInfomation = jobInfomation;
    }
}
