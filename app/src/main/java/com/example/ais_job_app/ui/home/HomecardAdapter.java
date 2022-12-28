package com.example.ais_job_app.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ais_job_app.CorpReqInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.DialCorpReqItemBinding;

import java.util.ArrayList;

public class HomecardAdapter extends RecyclerView.Adapter<HomecardAdapter.ViewHolder> {
    private ArrayList<Home_card> home_cards;
    private final Context context;

    public HomecardAdapter(ArrayList<Home_card> home_cards, Context context) {
        this.home_cards = home_cards;
        this.context = context;
    }


    public void setListItems(ArrayList<Home_card> list){
        this.home_cards = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomecardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.corp_rec_item, parent, false);
        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull HomecardAdapter.ViewHolder holder, int position) {
        Home_card homeCard = home_cards.get(position);
        holder.tv_text1.setText(homeCard.getHometext1());
        holder.tv_text2.setText(homeCard.getHometext2());
        //holder.iv_image.setImageDrawable(homeCard.getHometext1());


    }

    @Override
    public int getItemCount() {
        return home_cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_text1;
        private final TextView tv_text2;
        private final ImageView iv_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_text1 = itemView.findViewById(R.id.textView);
            tv_text2 = itemView.findViewById(R.id.textView2);
            iv_image = itemView.findViewById(R.id.imageView);


        }
    }
}
