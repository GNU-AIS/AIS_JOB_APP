package com.example.ais_job_app.ui.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.ais_job_app.CorpReqInfo;
import com.example.ais_job_app.JobCarrierInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.DialCorpReqItemBinding;

import java.util.ArrayList;

public class CorpReqInfoAdapter extends RecyclerView.Adapter<CorpReqInfoAdapter.ViewHolder> {
    private ArrayList<CorpReqInfo> corpReqInfos;
    private final Context context;

    public CorpReqInfoAdapter(ArrayList<CorpReqInfo> corpReqInfos, Context context) {
        this.corpReqInfos = corpReqInfos;
        this.context = context;
    }

    public void setListItems(ArrayList<CorpReqInfo> list){
        this.corpReqInfos = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CorpReqInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.corp_rec_item, parent, false);
        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull CorpReqInfoAdapter.ViewHolder holder, int position) {
        CorpReqInfo corpReqInfo = corpReqInfos.get(position);
        holder.tv_name.setText(corpReqInfo.getName());
        holder.tv_day.setText(corpReqInfo.getDay());
        holder.tv_title.setText(corpReqInfo.getTitle());
        holder.tv_pattern.setText(corpReqInfo.getCareer() + ", " + corpReqInfo.getEducation() + ", " + corpReqInfo.getPreference());
        holder.tv_career.setText(corpReqInfo.getPattern() + ", " + corpReqInfo.getSalary() + ", " + corpReqInfo.getTime());
        holder.tv_area.setText(corpReqInfo.getArea());

    }

    @Override
    public int getItemCount() {
        return corpReqInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        private final TextView tv_day;
        private final TextView tv_title;
        private final TextView tv_career;
        private final TextView tv_pattern;
        private final TextView tv_area;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_day = itemView.findViewById(R.id.tv_day);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_career = itemView.findViewById(R.id.tv_career);
            tv_pattern = itemView.findViewById(R.id.tv_pattern);
            tv_area = itemView.findViewById(R.id.tv_area);


            itemView.setOnClickListener(v->{
                CorpReqInfo corpReqInfo = corpReqInfos.get(getAdapterPosition());
                Dialog dialog = new Dialog(context);
                /* 다이얼로그 데이터 바인딩 */
                DialCorpReqItemBinding dialogBinding = DialCorpReqItemBinding.inflate(dialog.getLayoutInflater());
                dialog.setContentView(dialogBinding.getRoot());
                dialogBinding.tvName.setText(corpReqInfo.getName());
                dialogBinding.tvTitle.setText(corpReqInfo.getTitle());
                dialogBinding.tvAddr.setText(corpReqInfo.getBasicAddr());
                dialogBinding.tvDay.setText(corpReqInfo.getDay());
                dialogBinding.tvCareer.setText(corpReqInfo.getCareer());
                dialogBinding.tvPreference.setText(corpReqInfo.getPreference());
                dialogBinding.tvEducation.setText(corpReqInfo.getEducation());
                dialogBinding.tvPattern.setText(corpReqInfo.getPattern());
                dialogBinding.tvSalary.setText(corpReqInfo.getSalary());
                dialogBinding.tvTime.setText(corpReqInfo.getTime());
                dialogBinding.tvArea.setText(corpReqInfo.getArea());
                dialog.show();

                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            });

        }
    }
}
