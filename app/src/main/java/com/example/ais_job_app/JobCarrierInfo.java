package com.example.ais_job_app;

import java.io.Serializable;

public class JobCarrierInfo implements Serializable {

    private String corp_name;
    private String time;
    private String credit;
    private String toeic;
    private String toeic_sp;
    private String opic;
    private String certificate;
    private String intern;
    private String external_activities;
    private String overseas_study;
    private String awards;
    private String major;
    private String university;

    public JobCarrierInfo() {
    }

    public String getCorpName() {
        return corp_name;
    }

    public void setCorpName(String corpName) {
        this.corp_name = corpName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getToeic() {
        return toeic;
    }

    public void setToeic(String toeic) {
        this.toeic = toeic;
    }

    public String getToeic_sp() {
        return toeic_sp;
    }

    public void setToeic_sp(String toeic_sp) {
        this.toeic_sp = toeic_sp;
    }

    public String getOpeic() {
        return opic;
    }

    public void setOpeic(String opeic) {
        this.opic = opeic;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getIntern() {
        return intern;
    }

    public void setIntern(String intern) {
        this.intern = intern;
    }

    public String getExternalActivities() {
        return external_activities;
    }

    public void setExternalActivities(String externalActivities) {
        this.external_activities = externalActivities;
    }

    public String getOverseas_study() {
        return overseas_study;
    }

    public void setOverseas_study(String overseas_study) {
        this.overseas_study = overseas_study;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
