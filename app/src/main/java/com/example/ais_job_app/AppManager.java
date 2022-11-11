package com.example.ais_job_app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class AppManager {

    private HashMap<String, Float> map = new HashMap<>();

    private AppManager(){}
    private static final AppManager appManager = new AppManager();
    public static AppManager getInstance() { return appManager; }

    public void initPref(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        map.put("myCareerTgb", sharedPref.getFloat("myCareerTgb", 1.0f));
    }

    public void savePref(Activity activity, String key, float value){
        map.put(key, value);
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key, map.get(key) != null ? map.get(key) : 1.0f);
        editor.apply();
    }

    public HashMap<String, Float> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Float> map) {
        this.map = map;
    }
}
