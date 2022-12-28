package com.example.ais_job_app.ui.home;

public class Home_card {
    private String hometext1;
    private String hometext2;
    private String homeimage;

    public Home_card(String hometext,String hometext2,String homeimage) {
        this.hometext1 = hometext;
        this.hometext2 = hometext2;
        this.homeimage = homeimage;
    }

    public String getHometext1() {
        return hometext1;
    }

    public void setHometext1(String hometext1) {
        this.hometext1 = hometext1;
    }

    public String getHometext2() {
        return hometext2;
    }

    public void setHometext2(String hometext2) {
        this.hometext2 = hometext2;
    }

    public String getHomeimage() {
        return homeimage;
    }

    public void setHomeimage(String homeimage) {
        this.homeimage = homeimage;
    }
}
