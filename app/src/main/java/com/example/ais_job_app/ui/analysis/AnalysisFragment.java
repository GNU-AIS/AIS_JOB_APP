package com.example.ais_job_app.ui.analysis;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ais_job_app.AppManager;
import com.example.ais_job_app.ComRecInfo;
import com.example.ais_job_app.JobCarrierInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.FragmentAnalysisBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class AnalysisFragment extends Fragment {

    private FragmentAnalysisBinding binding;

    private ArrayList<JobCarrierInfo> jobCarrierInfoArrayList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /* 바인딩 설정 */
        binding = FragmentAnalysisBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /* 나의 커리어 토글 설정 */
        if (Float.compare(AppManager.getInstance().getMap().get("myCareerTgb"), 1.0f) == 0) {
            binding.tgb.setChecked(true);
            binding.clCdv.setVisibility(View.VISIBLE);
            binding.ivMenu.setVisibility(View.GONE);
        } else {
            binding.tgb.setChecked(false);
            binding.clCdv.setVisibility(View.GONE);
            binding.ivMenu.setVisibility(View.VISIBLE);
        }


        /* ui */
        binding.tvIsEmpty2.setText("표시할 수 있는 취업정보가 없어요");
        binding.tvIsEmpty.setText("텅...");

        /**/
        jobCarrierInfoArrayList = (ArrayList<JobCarrierInfo>) AppManager.getInstance().getJobCarrierInfoArrayList().clone();

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(), R.array.myCarrier, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //드롭다운뷰 연결
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //UI와 연결
        binding.spMyCarrier.setAdapter(adapter);

        // 나의 커리어 보기
        binding.tgb.setOnClickListener(v -> {
            if (binding.tgb.isChecked()) {
                binding.clCdv.setVisibility(View.VISIBLE);
                binding.ivMenu.setVisibility(View.GONE);
                AppManager.getInstance().savePref(requireActivity(), "myCareerTgb", 1.0f);

            } else {
                binding.clCdv.setVisibility(View.GONE);
                binding.ivMenu.setVisibility(View.VISIBLE);
                AppManager.getInstance().savePref(requireActivity(), "myCareerTgb", 0.0f);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        /* 기업이 없으면 글자 표시 */
        if(jobCarrierInfoArrayList.isEmpty()) {
            binding.tvIsEmpty.setVisibility(View.VISIBLE);
            binding.tvIsEmpty2.setVisibility(View.VISIBLE);
        } else {

            binding.tvIsEmpty.setVisibility(View.GONE);
            binding.tvIsEmpty2.setVisibility(View.GONE);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Spinner Listener
    public void spinnerListener() {
        binding.spMyCarrier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //선택 시 작동기능
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}