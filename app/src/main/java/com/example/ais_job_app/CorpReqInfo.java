package com.example.ais_job_app;

public class CorpReqInfo {
    private String name= "";;    // 기업 이름
    private String day= "";; // 모집일, 마감일
    private String title= "";;   // 모집 제목
    private String career= "";;  // 경력
    private String education= "";;   // 학력
    private String preference= "";;  // 우대
    private String pattern= "";; // 고용 형태
    private String salary= "";;  // 급여
    private String area= "";;    // 지역
    private String time = "";    // 시간
    private String profile = ""; // 이미지
    private String basicAddr= "";;   // 고용 주소

    public CorpReqInfo(String name, String day, String title, String career, String education, String preference, String pattern, String salary, String area, String time, String profile, String basicAddr) {
        this.name = name;
        this.day = day;
        this.title = title;
        this.career = career;
        this.education = education;
        this.preference = preference;
        this.pattern = pattern;
        this.salary = salary;
        this.area = area;
        this.time = time;
        this.profile = profile;
        this.basicAddr = basicAddr;
    }

    public String getBasicAddr() {
        return basicAddr;
    }

    public void setBasicAddr(String basicAddr) {
        this.basicAddr = basicAddr;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
