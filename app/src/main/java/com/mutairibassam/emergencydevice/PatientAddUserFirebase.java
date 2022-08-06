package com.mutairibassam.emergencydevice;

public class PatientAddUserFirebase {

    private String name;
    private String nationalid;
    private String relation;
    private String mobile;
    private String medication;

    public PatientAddUserFirebase() {

    }

    public PatientAddUserFirebase(String name, String nationalid, String badge, String mobile, String jobtitle) {
        this.name = name;
        this.nationalid = nationalid;
        this.relation = badge;
        this.mobile = mobile;
        this.medication = jobtitle;
    }

    //getter and setter for the user
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalid() {
        return nationalid;
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    public String getBadge() {
        return relation;
    }

    public void setBadge(String badge) {
        this.relation = badge;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobtitle() {
        return medication;
    }

    public void setJobtitle(String jobtitle) {
        this.medication = jobtitle;
    }
}
