package com.example.cuong.alumni.DataModel;

/**
 * Created by Cuong on 4/25/2018.
 */

public class User {

    private String userName;
    private String userPassword;
    private String dob;
    private String job;
    private String startYear;
    private String endYear;

    public User(String userName,String dob, String job, String startyear, String endYear) {
        this.userName = userName;
        this.dob=dob;
        this.job=job;
        this.startYear=startyear;
        this.endYear=endYear;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }
}
