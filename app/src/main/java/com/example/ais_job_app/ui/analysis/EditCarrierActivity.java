package com.example.ais_job_app.ui.analysis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ais_job_app.AppManager;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.ActivityEditCarrierBinding;

import java.util.HashMap;

public class EditCarrierActivity extends AppCompatActivity {
    private ActivityEditCarrierBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCarrierBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle("나의 커리어");
        
        binding.fab.setOnClickListener(v -> {
            saveCarrier();    
        });

        HashMap<String, Float> mapCarrier = AppManager.getInstance().getMapCarrier();
        binding.etCredit.setText(mapCarrier.get("credit").toString());
        binding.etToeic.setText(mapCarrier.get("toeic").toString());
        binding.etToeicSp.setText(mapCarrier.get("toeic_sp").toString());
        binding.etOpic.setText(mapCarrier.get("opic").toString());
        binding.etExternalActivities.setText(mapCarrier.get("external_activities").toString());
        binding.etCertificate.setText(mapCarrier.get("certificate").toString());
        binding.etIntern.setText(mapCarrier.get("intern").toString());
        binding.etAwards.setText(mapCarrier.get("awards").toString());
        binding.etOverseasStudy.setText(mapCarrier.get("overseas_study").toString());

    }

    private void saveCarrier() {
        HashMap<String, Float> mapCarrier = new HashMap<>();
        if(binding.etCredit.getText().toString().equals("") || binding.etToeic.getText().toString().equals("") || binding.etToeicSp.getText().toString().equals("") ||
                binding.etOpic.getText().toString().equals("") || binding.etExternalActivities.getText().toString().equals("") || binding.etCertificate.getText().toString().equals("") ||
                binding.etIntern.getText().toString().equals("") || binding.etAwards.getText().toString().equals("") ||
                binding.etOverseasStudy.getText().toString().equals("")){
            Toast.makeText(this, "입력되지 않은 빈칸이 있습니다.", Toast.LENGTH_SHORT).show();

        } else{
            mapCarrier.put("credit", Float.valueOf(String.valueOf(binding.etCredit.getText())));
            mapCarrier.put("toeic", Float.valueOf(String.valueOf( binding.etToeic.getText())));
            mapCarrier.put("toeic_sp", Float.valueOf(String.valueOf(binding.etToeicSp.getText())));
            mapCarrier.put("opic", Float.valueOf(String.valueOf(binding.etOpic.getText())));
            mapCarrier.put("external_activities", Float.valueOf(String.valueOf(binding.etExternalActivities.getText())));
            mapCarrier.put("certificate", Float.valueOf(String.valueOf(binding.etCertificate.getText())));
            mapCarrier.put("intern", Float.valueOf(String.valueOf(binding.etIntern.getText())));
            mapCarrier.put("awards", Float.valueOf(String.valueOf(binding.etAwards.getText())));
            mapCarrier.put("overseas_study", Float.valueOf(String.valueOf(binding.etOverseasStudy.getText())));

            AppManager.getInstance().setMapCarrier(mapCarrier);

            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            for(String key : mapCarrier.keySet()) {
                Log.d("__test__", key);
                editor.putFloat(key, mapCarrier.get(key) != null ? mapCarrier.get(key) : 0.0f);
            }
            editor.apply();
            Toast.makeText(this, "커리어가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }
}