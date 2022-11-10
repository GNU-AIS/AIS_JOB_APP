package com.example.ais_job_app.ui.analysis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ais_job_app.databinding.FragmentAnalysisBinding;


public class AnalysisFragment extends Fragment {

    private FragmentAnalysisBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAnalysisBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textAnalysis.setText("취업 분석 화면");

        // 나의 커리어 보기
        binding.tgb.setOnClickListener(v->{
            if(binding.tgb.isChecked()){
                binding.clCdv.setVisibility(View.VISIBLE);
                binding.ivMenu.setVisibility(View.GONE);
            } else {
                binding.clCdv.setVisibility(View.GONE);
                binding.ivMenu.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}