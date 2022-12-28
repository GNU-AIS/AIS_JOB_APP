package com.example.ais_job_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.ais_job_app.AppManager;
import com.example.ais_job_app.CorpReqInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.FragmentHomeBinding;
import com.example.ais_job_app.ui.dashboard.CorpReqInfoAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageView graph_image;
    private ArrayList<Home_card> home_cards = new ArrayList<>();

    private CorpReqInfoAdapter corpReqInfoAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Glide.with(requireContext())
                .load("http://175.200.108.201:5050/wordcloud")
                .into(binding.imageView2);

        Glide.with(requireContext())
                .load("http://175.200.108.201:5050/graphmonth")
                .into(binding.imageView3);

        Glide.with(requireContext())
                .load("http://175.200.108.201:5050/graphdate")
                .into(binding.imageView4);

        Glide.with(requireContext())
                .load("http://175.200.108.201:5050/graphtime")
                .into(binding.imageView5);



        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}