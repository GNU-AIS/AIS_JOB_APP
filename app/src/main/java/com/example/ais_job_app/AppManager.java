package com.example.ais_job_app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

public class AppManager {

    /* 필드 */
    private HashMap<String, Float> map = new HashMap<>();
    private HashMap<String, Float> mapCarrier = new HashMap<>();

    private ArrayList<CorpReqInfo> corpReqInfoArrayList = new ArrayList<>();
    private ArrayList<JobCarrierInfo> jobCarrierInfoArrayList = new ArrayList<>();



    /* 싱글톤 구조 */
    private AppManager(){}
    private static final AppManager appManager = new AppManager();
    public static AppManager getInstance() { return appManager; }

    /* 데이터 저장 */
    public void initPref(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        map.put("myCareerTgb", sharedPref.getFloat("myCareerTgb", 1.0f));

        mapCarrier.put("credit", sharedPref.getFloat("credit", 0.0f));
        mapCarrier.put("toeic", sharedPref.getFloat("toeic", 0.0f));
        mapCarrier.put("toeic_sp", sharedPref.getFloat("toeic_sp", 0.0f));
        mapCarrier.put("opic", sharedPref.getFloat("opic", 0.0f));
        mapCarrier.put("certificate", sharedPref.getFloat("certificate", 0.0f));
        mapCarrier.put("intern", sharedPref.getFloat("intern", 0.0f));
        mapCarrier.put("awards", sharedPref.getFloat("awards", 0.0f));
        mapCarrier.put("overseas_study", sharedPref.getFloat("overseas_study", 0.0f));
        mapCarrier.put("external_activities", sharedPref.getFloat("external_activities", 0.0f));

    }

    public void savePref(Activity activity, String key, float value){
        map.put(key, value);
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key, map.get(key) != null ? map.get(key) : 1.0f);
        editor.apply();
    }

    /* Getter & Setter */



    public HashMap<String, Float> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Float> map) {
        this.map = map;
    }

    public ArrayList<CorpReqInfo> getCorpReqInfoArrayList() {
        return corpReqInfoArrayList;
    }

    public void setCorpReqInfoArrayList(ArrayList<CorpReqInfo> corpReqInfoArrayList) {
        this.corpReqInfoArrayList = corpReqInfoArrayList;
    }

    public ArrayList<JobCarrierInfo> getJobCarrierInfoArrayList() {
        return jobCarrierInfoArrayList;
    }

    public void setJobCarrierInfoArrayList(ArrayList<JobCarrierInfo> jobCarrierInfoArrayList) {
        this.jobCarrierInfoArrayList = jobCarrierInfoArrayList;
    }

    public HashMap<String, Float> getMapCarrier() {
        return mapCarrier;
    }

    public void setMapCarrier(HashMap<String, Float> mapCarrier) {
        this.mapCarrier = mapCarrier;
    }



}
