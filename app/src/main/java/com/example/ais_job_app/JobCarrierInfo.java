package com.example.ais_job_app;

public class JobCarrierInfo implements Cloneable{
    private String title;

    @Override
    protected JobCarrierInfo clone() throws CloneNotSupportedException {
        return (JobCarrierInfo) super.clone();
    }
}
