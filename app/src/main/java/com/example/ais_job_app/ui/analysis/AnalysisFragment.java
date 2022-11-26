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
import com.example.ais_job_app.JobCarrierInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.FragmentAnalysisBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class AnalysisFragment extends Fragment {

    private FragmentAnalysisBinding binding;

    private ArrayList<JobCarrierInfo> jobCarrierInfoArrayList = new ArrayList<>();

    /* 버튼 애니메이션 */
    private Animation rotateOpen, rotateClose, fromBottom, toBottom;
    private Boolean fabClickedFlag = false;


    /**/

    String testUrl = "http://175.200.108.201:4000/test";

    Handler handler = new Handler();// Thread에서 전달받은 값을 메인으로 가져오기 위한 Handler

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

        binding.tvIsEmpty2.setText("표시할 수 있는 취업정보가 없어요");
        binding.tvIsEmpty.setText("텅...");

        initCarrierScore();

        /**/
        jobCarrierInfoArrayList = (ArrayList<JobCarrierInfo>) AppManager.getInstance().getJobCarrierInfoArrayList().clone();

        RequestThread thread = new RequestThread(); // Thread 생성
        thread.start(); // Thread 시작





        return binding.getRoot();
    }
    class RequestThread extends Thread { // url을 읽을 때도 앱이 동작할 수 있게 하기 위해 Thread 생성
        @Override
        public void run() { // 이 쓰레드에서 실행 될 메인 코드
            try {
                URL url = new URL(testUrl); // 입력받은 웹서버 URL 저장
                HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // url에 연결
                if(conn != null){ // 만약 연결이 되었을 경우
                    conn.setConnectTimeout(10000); // 10초 동안 기다린 후 응답이 없으면 종료
                    conn.setRequestMethod("POST"); // GET 메소드 : 웹 서버로 부터 리소스를 가져온다.
                    conn.setDoInput(true); // 서버에서 온 데이터를 입력받을 수 있는 상태인가? true
                    conn.setDoOutput(true); // 서버에서 온 데이터를 출력할 수 있는 상태인가? true

                    int resCode = conn.getResponseCode(); // 응답 코드를 리턴 받는다.
                    if(resCode == HttpURLConnection.HTTP_OK){ // 만약 응답 코드가 200(=OK)일 경우
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        // BufferedReader() : 엔터만 경계로 인식하고 받은 데이터를 String 으로 고정, Scanner 에 비해 빠름!
                        // InputStreamReader() : 지정된 문자 집합 내의 문자로 인코딩
                        // getInputStream() : url 에서 데이터를 읽어옴
                        String line = null; // 웹에서 가져올 데이터를 저장하기위한 변수
                        while(true){
                            line = reader.readLine(); // readLine() : 한 줄을 읽어오는 함수
                            if(line == null) // 만약 읽어올 줄이 없으면 break
                                break;
                            println(line); // 출력 *80번째 줄의 함수*
                        }
                        reader.close(); // 입력이 끝남
                    }
                    conn.disconnect(); // DB연결 해제
                }
            } catch (Exception e) { //예외 처리
                e.printStackTrace(); // printStackTrace() : 에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력
            }
        }
    }

    public void println(final String data){ // final : 변수의 상수화 => 변수 변경 불가
        handler.post(new Runnable() {
            // post() : 핸들러에서 쓰레드로 ()를 보냄
            // Runnable() : 실행 코드가 담긴 객체
            @Override
            public void run() {
                binding.testText.append(data);
            } // run() : 실행될 코드가 들어있는 메소드
        });
    }


    private void initCarrierScore() {
        HashMap<String, Float> mapCarrier = AppManager.getInstance().getMapCarrier();
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