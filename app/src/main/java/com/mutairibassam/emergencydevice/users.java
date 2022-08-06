package com.mutairibassam.emergencydevice;

public class users {

    private String name;
    private String nationalid;
    private String badge;
    private String mobile;
    private String jobtitle;

    public users() {

    }

    public users(String name, String nationalid, String badge, String mobile, String jobtitle) {
        this.name = name;
        this.nationalid = nationalid;
        this.badge = badge;
        this.mobile = mobile;
        this.jobtitle = jobtitle;
    }

    //Getter and Setter for the users

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
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }
}
