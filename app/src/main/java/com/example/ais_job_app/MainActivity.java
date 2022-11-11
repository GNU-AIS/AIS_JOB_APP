package com.example.ais_job_app;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ais_job_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 바인딩(원래는 객체를 생성하고 R.id를 넣어야 하지만
        // 바인딩을 하므로서 객체를 생성하지 않고 binding.id 이런식으로 쓰는게 가능해짐
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 앱바로 어디 어디 프레그먼트를 불러올지 미리 설정
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_analysis, R.id.navigation_info)
                .build();

        // 커스텀 앱바를 만들기 위해 툴바를 만듬
        // 하단 네비게이션 바와 커스텀 앱바의 이동과 이름 같은 설정을 위한 코드
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        // 설정
        setInit();
    }

    private void setInit() {
        AppManager.getInstance().initPref(this);

    }

}