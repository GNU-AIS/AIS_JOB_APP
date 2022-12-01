package com.example.ais_job_app.ui.analysis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ais_job_app.JobCarrierInfo;
import com.example.ais_job_app.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AnalysisAdapter extends RecyclerView.Adapter<AnalysisAdapter.ViewHolder>{

    ArrayList<JobCarrierInfo> jobCarrierInfos;
    Context context;
    HashMap<String, Float> mapCarrier;
    Drawable upImage, downImage;

    @SuppressLint("UseCompatLoadingForDrawables")
    public AnalysisAdapter(ArrayList<JobCarrierInfo> jobCarrierInfos, Context context, HashMap<String, Float> mapCarrier) {
        this.jobCarrierInfos = jobCarrierInfos;
        this.context = context;
        this.mapCarrier = mapCarrier;
        this.upImage = context.getDrawable(R.drawable.ic_up_red);
        this.downImage = context.getDrawable(R.drawable.ic_down_blue);
    }

    @NonNull
    @Override
    public AnalysisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.corp_analysis_item, parent, false);
        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull AnalysisAdapter.ViewHolder holder, int position) {
        JobCarrierInfo jobCarrierInfo = jobCarrierInfos.get(position);

        holder.tv_name.setText(jobCarrierInfo.getCorpName());
        holder.tv_time.setText(jobCarrierInfo.getTime());

        holder.tv_credit_n.setText(jobCarrierInfo.getCredit());
        holder.tv_toeic_n.setText(jobCarrierInfo.getToeic());
        holder.tv_toeic_sp_n.setText(jobCarrierInfo.getCertificate());
        holder.tv_intern_n.setText(jobCarrierInfo.getIntern());
        holder.tv_opeic_n.setText(jobCarrierInfo.getOpeic());
        holder.tv_overseas_study_n.setText(jobCarrierInfo.getOverseasStudy());
        holder.tv_external_activities_n.setText(jobCarrierInfo.getExternalActivities());
        holder.tv_awards_n.setText(jobCarrierInfo.getAwards());

        if (mapCarrier.get("credit") < Float.parseFloat(jobCarrierInfo.getCredit())) holder.tv_credit.setCompoundDrawables(null, null, upImage, null);
        else if (mapCarrier.get("credit") > Float.parseFloat(jobCarrierInfo.getCredit())) holder.tv_credit.setCompoundDrawables(null, null, downImage, null);
        else holder.tv_credit.setCompoundDrawables(null, null, null, null);

        if (mapCarrier.get("toeic") < Float.parseFloat(jobCarrierInfo.getToeic())) holder.tv_toeic.setCompoundDrawables(null, null, upImage, null);
        else if (mapCarrier.get("toeic") > Float.parseFloat(jobCarrierInfo.getToeic())) holder.tv_toeic.setCompoundDrawables(null, null, downImage, null);
        else holder.tv_toeic.setCompoundDrawables(null, null, null, null);

        if (mapCarrier.get("toeic_sp") < Float.parseFloat(jobCarrierInfo.getToeic_sp())) holder.tv_toeic_sp.setCompoundDrawables(null, null, upImage, null);
        else if (mapCarrier.get("toeic_sp") > Float.parseFloat(jobCarrierInfo.getToeic_sp())) holder.tv_toeic_sp.setCompoundDrawables(null, null, downImage, null);
        else holder.tv_toeic_sp.setCompoundDrawables(null, null, null, null);

        if (mapCarrier.get("intern") < Float.parseFloat(jobCarrierInfo.getIntern())) holder.tv_intern.setCompoundDrawables(null, null, upImage, null);
        else if (mapCarrier.get("intern") > Float.parseFloat(jobCarrierInfo.getIntern())) holder.tv_intern.setCompoundDrawables(null, null, downImage, null);
        else holder.tv_intern.setCompoundDrawables(null, null, null, null);

        if (mapCarrier.get("opic") < Float.parseFloat(jobCarrierInfo.getOpeic())) holder.tv_opeic.setCompoundDrawables(null, null, upImage, null);
        else if (mapCarrier.get("opic") > Float.parseFloat(jobCarrierInfo.getOpeic())) holder.tv_opeic.setCompoundDrawables(null, null, downImage, null);
        else holder.tv_opeic.setCompoundDrawables(null, null, null, null);

        if (mapCarrier.get("overseas_study") < Float.parseFloat(jobCarrierInfo.getOverseasStudy())) holder.tv_overseas_study.setCompoundDrawables(null, null, upImage, null);
        else if (mapCarrier.get("overseas_study") > Float.parseFloat(jobCarrierInfo.getOverseasStudy())) holder.tv_overseas_study.setCompoundDrawables(null, null, downImage, null);
        else holder.tv_overseas_study.setCompoundDrawables(null, null, null, null);

        if (mapCarrier.get("external_activities") < Float.parseFloat(jobCarrierInfo.getExternalActivities())) holder.tv_external_activities.setCompoundDrawables(null, null, upImage, null);
        else if (mapCarrier.get("external_activities") > Float.parseFloat(jobCarrierInfo.getExternalActivities())) holder.tv_external_activities.setCompoundDrawables(null, null, downImage, null);
        else holder.tv_external_activities.setCompoundDrawables(null, null, null, null);

        if (mapCarrier.get("awards") < Float.parseFloat(jobCarrierInfo.getAwards())) holder.tv_awards.setCompoundDrawables(null, null, upImage, null);
        else if (mapCarrier.get("awards") > Float.parseFloat(jobCarrierInfo.getAwards())) holder.tv_awards.setCompoundDrawables(null, null, downImage, null);
        else holder.tv_external_activities.setCompoundDrawables(null, null, null, null);
    }

    @Override
    public int getItemCount() {
        return jobCarrierInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_time;

        TextView tv_credit, tv_toeic, tv_toeic_sp, tv_certificate
                , tv_intern, tv_opeic, tv_overseas_study, tv_external_activities
                , tv_awards;

        TextView tv_credit_n, tv_toeic_n, tv_toeic_sp_n, tv_certificate_n
                , tv_intern_n, tv_opeic_n, tv_overseas_study_n, tv_external_activities_n
                , tv_awards_n;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);

            tv_credit = itemView.findViewById(R.id.tv_credit);
            tv_toeic = itemView.findViewById(R.id.tv_toeic);
            tv_toeic_sp = itemView.findViewById(R.id.tv_toeic_sp);
            tv_certificate = itemView.findViewById(R.id.tv_certificate);
            tv_intern = itemView.findViewById(R.id.tv_intern);
            tv_opeic = itemView.findViewById(R.id.tv_opeic);
            tv_overseas_study = itemView.findViewById(R.id.tv_overseas_study);
            tv_external_activities = itemView.findViewById(R.id.tv_external_activities);
            tv_awards = itemView.findViewById(R.id.tv_awards);

            tv_credit_n = itemView.findViewById(R.id.tv_credit_n);
            tv_toeic_n = itemView.findViewById(R.id.tv_toeic_n);
            tv_toeic_sp_n = itemView.findViewById(R.id.tv_toeic_sp_n);
            tv_certificate_n = itemView.findViewById(R.id.tv_certificate_n);
            tv_intern_n = itemView.findViewById(R.id.tv_intern_n);
            tv_opeic_n = itemView.findViewById(R.id.tv_opeic_n);
            tv_overseas_study_n = itemView.findViewById(R.id.tv_overseas_study_n);
            tv_external_activities_n = itemView.findViewById(R.id.tv_external_activities_n);
            tv_awards_n = itemView.findViewById(R.id.tv_awards_n);

        }
    }
}
