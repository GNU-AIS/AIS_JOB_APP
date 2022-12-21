package com.example.ais_job_app.ui.analysis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.ais_job_app.AppManager;
import com.example.ais_job_app.JobCarrierInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.ActivityAnalysisViewBinding;

import java.util.HashMap;

public class AnalysisViewActivity extends AppCompatActivity {
    private ActivityAnalysisViewBinding binding;
    private HashMap<String, Float> mapCarrier = AppManager.getInstance().getMapCarrier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnalysisViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle("비교");

        Intent intent = getIntent();
        JobCarrierInfo jobCarrierInfo = (JobCarrierInfo) intent.getSerializableExtra("job");

        // 위 카드뷰
        binding.tvName.setText(jobCarrierInfo.getCorpName());
        binding.tvTime.setText(jobCarrierInfo.getTime());

        binding.tvCreditN.setText(jobCarrierInfo.getCredit());
        binding.tvToeicN.setText(jobCarrierInfo.getToeic());
        binding.tvToeicSpN.setText(jobCarrierInfo.getToeic_sp());
        binding.tvCertificateN.setText(jobCarrierInfo.getCertificate());
        binding.tvInternN.setText(jobCarrierInfo.getIntern());
        binding.tvOpeicN.setText(jobCarrierInfo.getOpeic());
        binding.tvOverseasStudyN.setText(jobCarrierInfo.getOverseas_study());
        binding.tvExternalActivitiesN.setText(jobCarrierInfo.getExternalActivities());
        binding.tvAwardsN.setText(jobCarrierInfo.getAwards());
        
        // 아래 카드뷰
        float credit = mapCarrier.get("credit");
        float toeic = mapCarrier.get("toeic");
        float toeicSp = mapCarrier.get("toeic_sp");
        float opic = mapCarrier.get("opic");
        float certificate = mapCarrier.get("certificate");
        float intern = mapCarrier.get("intern");
        float awards = mapCarrier.get("awards");
        float overseasStudy = mapCarrier.get("overseas_study");
        float extern = mapCarrier.get("external_activities");

        binding.tvMyCredit.setText(String.valueOf(credit));
        binding.tvMyToeic.setText(String.valueOf((int) toeic));
        binding.tvMyToeicSp.setText(String.valueOf((int) toeicSp));
        binding.tvMyCertificate.setText(String.valueOf((int) certificate));
        binding.tvMyIntern.setText(String.valueOf((int) intern));
        binding.tvMyOpeic.setText(String.valueOf((int) opic));
        binding.tvMyOverseasStudy.setText(String.valueOf((int) overseasStudy));
        binding.tvMyExternalActivities.setText(String.valueOf((int) extern));
        binding.tvMyAwards.setText(String.valueOf((int) awards));

        Drawable red = getResources().getDrawable(R.drawable.ic_up_red, null);
        Drawable blue = getResources().getDrawable(R.drawable.ic_down_blue, null);

        if(credit >= Float.parseFloat(jobCarrierInfo.getCredit())) binding.tvCreditC.setCompoundDrawablesWithIntrinsicBounds(null, null, red, null);
        else binding.tvCreditC.setCompoundDrawablesWithIntrinsicBounds(null, null, blue, null);

        if(toeic >= Float.parseFloat(jobCarrierInfo.getToeic())) binding.tvToeicC.setCompoundDrawablesWithIntrinsicBounds(null, null, red, null);
        else binding.tvToeicC.setCompoundDrawablesWithIntrinsicBounds(null, null, blue, null);

        if(certificate >= Float.parseFloat(jobCarrierInfo.getCertificate())) binding.tvCertificateC.setCompoundDrawablesWithIntrinsicBounds(null, null, red, null);
        else binding.tvCertificateC.setCompoundDrawablesWithIntrinsicBounds(null, null, blue, null);

        if(intern >= Float.parseFloat(jobCarrierInfo.getIntern())) binding.tvInternC.setCompoundDrawablesWithIntrinsicBounds(null, null, red, null);
        else binding.tvInternC.setCompoundDrawablesWithIntrinsicBounds(null, null, blue, null);

        if(overseasStudy >= Float.parseFloat(jobCarrierInfo.getOverseas_study())) binding.tvOverseasStudyC.setCompoundDrawablesWithIntrinsicBounds(null, null, red, null);
        else binding.tvOverseasStudyC.setCompoundDrawablesWithIntrinsicBounds(null, null, blue, null);

        if(extern >= Float.parseFloat(jobCarrierInfo.getExternalActivities())) binding.tvExternalActivitiesC.setCompoundDrawablesWithIntrinsicBounds(null, null, red, null);
        else binding.tvExternalActivitiesC.setCompoundDrawablesWithIntrinsicBounds(null, null, blue, null);

        if(awards >= Float.parseFloat(jobCarrierInfo.getAwards())) binding.tvAwardsC.setCompoundDrawablesWithIntrinsicBounds(null, null, red, null);
        else binding.tvAwardsC.setCompoundDrawablesWithIntrinsicBounds(null, null, blue, null);
    }
}