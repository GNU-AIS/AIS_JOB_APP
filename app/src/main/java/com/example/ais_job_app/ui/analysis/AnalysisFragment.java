package com.example.ais_job_app.ui.analysis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ais_job_app.AppManager;
import com.example.ais_job_app.CorpReqInfo;
import com.example.ais_job_app.JobCarrierInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.FragmentAnalysisBinding;
import com.example.ais_job_app.ui.dashboard.CorpReqInfoAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class AnalysisFragment extends Fragment {

    private FragmentAnalysisBinding binding;

    HashMap<String, Float> mapCarrier = AppManager.getInstance().getMapCarrier();
    private ArrayList<JobCarrierInfo> jobCarrierInfoArrayList = AppManager.getInstance().getJobCarrierInfoArrayList();
    private AnalysisAdapter analysisAdapter;

    /* 버튼 애니메이션 */
    private Animation rotateOpen, rotateClose, fromBottom, toBottom;
    private Boolean fabClickedFlag = false;


    /**/


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /* 바인딩 설정 */
        binding = FragmentAnalysisBinding.inflate(inflater, container, false);

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
        rotateOpen = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_open_an);
        rotateClose = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_close_an);
        fromBottom = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_from_bottom_an);
        toBottom = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_to_bottom_an);



        initCarrierScore();

        binding.tvIsEmpty2.setText("표시할 수 있는 커리어가 없어요");
        binding.tvIsEmpty.setText("텅...");
        if(!jobCarrierInfoArrayList.isEmpty()){
            binding.tvIsEmpty.setVisibility(View.GONE);
            binding.tvIsEmpty2.setVisibility(View.GONE);
        }

        /**/
        jobCarrierInfoArrayList = AppManager.getInstance().getJobCarrierInfoArrayList();

        return binding.getRoot();
    }


    private void initCarrierScore() {
        float credit = mapCarrier.get("credit");
        float toeic = mapCarrier.get("toeic");
        float toeicSp = mapCarrier.get("toeic_sp");
        float opic = mapCarrier.get("opic");
        float foreign_lan = mapCarrier.get("foreign_lan");
        float certificate = mapCarrier.get("certificate");
        float intern = mapCarrier.get("intern");
        float volunteer = mapCarrier.get("volunteer");
        float awards = mapCarrier.get("awards");
        float overseasStudy = mapCarrier.get("overseas_study");

        binding.tvCredit.setText("학점: " + credit);
        binding.tvToeic.setText("토익: " + (int)toeic);
        binding.tvToeicSp.setText("토익스피킹: " + (int)toeicSp);
        binding.tvOpeic.setText("OPIC: " + (int)opic);
        binding.tvForeignLan.setText("기타외국어: " + (int)foreign_lan + "개");
        binding.tvCertificate.setText("자격증: " + (int)certificate + "개");
        binding.tvIntern.setText("인턴: " + (int)intern + "개월");
        binding.tvVolunteer.setText("봉사활동: " + (int)volunteer + "시간");
        binding.tvAwards.setText("교내/외 수상: " + (int)awards + "회");
        binding.tvOverseasStudy.setText("해외경험: " + (int)overseasStudy + "회" );
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

        /* fab 열기/닫기 버튼 */
        binding.fab.setOnClickListener(v->{
            onOpenFabClick();
        });
        binding.fabEdit.setOnClickListener(v->{
            Intent intent = new Intent(requireActivity(), EditCarrierActivity.class);
            startActivity(intent);
        });
        binding.fabClear.setOnClickListener(v->{
            HashMap<String, Float> mapCarrier = new HashMap<>();
            mapCarrier.put("credit", 0.0f);
            mapCarrier.put("toeic", 0.0f);
            mapCarrier.put("toeic_sp", 0.0f);
            mapCarrier.put("opic", 0.0f);
            mapCarrier.put("foreign_lan", 0.0f);
            mapCarrier.put("certificate", 0.0f);
            mapCarrier.put("intern", 0.0f);
            mapCarrier.put("volunteer", 0.0f);
            mapCarrier.put("awards", 0.0f);
            mapCarrier.put("overseas_study", 0.0f);
            AppManager.getInstance().setMapCarrier(mapCarrier);
            SharedPreferences sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            for(String key : mapCarrier.keySet()) {
                Log.d("__test__", key);
                editor.putFloat(key, mapCarrier.get(key) != null ? mapCarrier.get(key) : 0.0f);
            }
            editor.apply();
            Toast.makeText(requireContext(), "커리어가 초기화 되었습니다.", Toast.LENGTH_SHORT).show();
            onResume();
        });

        analysisAdapter = new AnalysisAdapter(jobCarrierInfoArrayList, requireContext(), mapCarrier);

        binding.rv.setHasFixedSize(true);

        binding.rv.setAdapter(analysisAdapter);

    }

    private void onOpenFabClick() {
        setVisibility(fabClickedFlag);
        setAnimation(fabClickedFlag);
        fabClickedFlag = !fabClickedFlag;
    }

    private void setAnimation(Boolean flag) {
        if(!flag) {
            binding.fabClear.setVisibility(View.VISIBLE);
            binding.fabEdit.setVisibility(View.VISIBLE);
        } else {
            binding.fabClear.setVisibility(View.INVISIBLE);
            binding.fabEdit.setVisibility(View.INVISIBLE);
        }
    }

    private void setVisibility(Boolean flag) {
        if(!flag) {
            binding.fabClear.startAnimation(fromBottom);
            binding.fabEdit.startAnimation(fromBottom);
            binding.fab.setImageResource(R.drawable.ic_baseline_add_24);
            binding.fab.startAnimation(rotateOpen);
        } else {
            binding.fabClear.startAnimation(toBottom);
            binding.fabEdit.startAnimation(toBottom);
            binding.fab.setImageResource(R.drawable.ic_baseline_edit_note_24);
            binding.fab.startAnimation(rotateClose);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        initCarrierScore();
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