package com.example.ais_job_app.ui.info;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.FragmentAnalysisBinding;
import com.example.ais_job_app.databinding.FragmentInfoBinding;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {

    private ListView list;

    private FragmentInfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textInfo.setText("앱 기타 정보 화면");
        //test

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,data);
        binding.list.setAdapter(adapter);


        data.add("ALS 동아리");
        data.add("앱 활동 기획");
        data.add("앱 문의");
        data.add("앱 정보");
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}