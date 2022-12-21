package com.example.ais_job_app;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ais_job_app.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final Handler handler = new Handler();// Thread에서 전달받은 값을 메인으로 가져오기 위한 Handler

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 바인딩(원래는 객체를 생성하고 R.id를 넣어야 하지만
        // 바인딩을 하므로서 객체를 생성하지 않고 binding.id 이런식으로 쓰는게 가능해짐
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // 설정
        AppManager.getInstance().initPref(this);
        // 앱바로 어디 어디 프레그먼트를 불러올지 미리 설정
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_analysis, R.id.navigation_info)
                .build();

        // 커스텀 앱바를 만들기 위해 툴바를 만듬
        // 하단 네비게이션 바와 커스텀 앱바의 이동과 이름 같은 설정을 위한 코드
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        RequestThread thread = new RequestThread("http://175.200.108.201:5050/corpoutput", 1); // Thread 생성
        thread.start(); // Thread 시작
        RequestThread thread2 = new RequestThread("http://175.200.108.201:5050/careeroutput", 2); // Thread 생성
        thread2.start(); // Thread 시작

    }

    class RequestThread extends Thread { // url을 읽을 때도 앱이 동작할 수 있게 하기 위해 Thread 생성
        String u;
        int flag;
        RequestThread(String f, int flag){
            this.u = f;
            this.flag = flag;
        }

        @Override
        public void run() { // 이 쓰레드에서 실행 될 메인 코드
            try {
                URL url = new URL(u); // 입력받은 웹서버 URL 저장
                HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // url에 연결
                if (conn != null) { // 만약 연결이 되었을 경우
                    conn.setConnectTimeout(10000); // 10초 동안 기다린 후 응답이 없으면 종료
                    conn.setRequestMethod("POST"); // GET 메소드 : 웹 서버로 부터 리소스를 가져온다.
                    conn.setDoInput(true); // 서버에서 온 데이터를 입력받을 수 있는 상태인가? true
                    conn.setDoOutput(true); // 서버에서 온 데이터를 출력할 수 있는 상태인가? true

                    int resCode = conn.getResponseCode(); // 응답 코드를 리턴 받는다.
                    if (resCode == HttpURLConnection.HTTP_OK) { // 만약 응답 코드가 200(=OK)일 경우
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        // BufferedReader() : 엔터만 경계로 인식하고 받은 데이터를 String 으로 고정, Scanner 에 비해 빠름!
                        // InputStreamReader() : 지정된 문자 집합 내의 문자로 인코딩
                        // getInputStream() : url 에서 데이터를 읽어옴
                        String line = null; // 웹에서 가져올 데이터를 저장하기위한 변수
                        while (true) {
                            line = reader.readLine(); // readLine() : 한 줄을 읽어오는 함수
                            if (line == null) // 만약 읽어올 줄이 없으면 break
                                break;
                            println(line, flag);
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

    public void println(final String data, int flag){ // final : 변수의 상수화 => 변수 변경 불가
        handler.post(new Runnable() {
            // post() : 핸들러에서 쓰레드로 ()를 보냄
            // Runnable() : 실행 코드가 담긴 객체
            @Override
            public void run() {
                if (flag == 2) {
                    ArrayList<JobCarrierInfo> list = new Gson().fromJson(data, new TypeToken<ArrayList<JobCarrierInfo>>(){}.getType());
                    AppManager.getInstance().setJobCarrierInfoArrayList(list);
                } else if (flag == 1){
                    ArrayList<CorpReqInfo> list = new Gson().fromJson(data, new TypeToken<ArrayList<CorpReqInfo>>(){}.getType());
                    AppManager.getInstance().setCorpReqInfoArrayList(list);
                }
            }
        });
    }

}