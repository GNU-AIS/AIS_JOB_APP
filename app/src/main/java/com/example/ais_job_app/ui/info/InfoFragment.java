package com.example.ais_job_app.ui.info;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.FragmentInfoBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InfoFragment extends Fragment {

    private ExpandalbeListViewAdapter listViewAdapter;
    private List<String> chapterList;
    private HashMap<String,List<String>> topicList;
    private FragmentInfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        showList();
        listViewAdapter = new ExpandalbeListViewAdapter(requireContext(),chapterList,topicList);
        binding.eListView.setAdapter(listViewAdapter);
        return binding.getRoot();
    }

    private void showList() {

        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        chapterList.add("ALS 동아리");
        chapterList.add("앱 활동 기획");
        chapterList.add("앱 문의");
        chapterList.add("앱 정보");
        chapterList.add("앱 기타");

        List<String> topic1 = new ArrayList<>();
        topic1.add("19학번 컴퓨터공학과 학생들이 모인 경상국립대 동아리 입니다.");
        topic1.add("안드로이드 멤버 : 김주영 이동현");
        topic1.add("웹크롤링 멤버 : 박지홍 차도훈");
        topic1.add("서버 멤버 : 김민기 최해영");

        List<String> topic2 = new ArrayList<>();
        topic2.add("9.7 ~ 10.26 웹크롤링 공부");
        topic2.add("11.2 ~ 현재 진행형 앱 개발");


        List<String> topic3 = new ArrayList<>();
        topic3.add("경상국립대학교 컴퓨터공학과 김주영을 불러주세용");


        List<String> topic4 = new ArrayList<>();
        topic4.add("안드로이드 스튜디오로 개발하였으며 파이참과 api를 이용해 서버연결 및 웹크롤링으로 여러가지 정보를 제공해주는 앱입니다.");



        List<String> topic5 = new ArrayList<>();
        topic5.add("통기타~~");
        topic5.add("일렉트로 기타~~");
        topic5.add("깔깔깔깔 ㅎㅎ");

        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic2);
        topicList.put(chapterList.get(2),topic3);
        topicList.put(chapterList.get(3),topic4);
        topicList.put(chapterList.get(4),topic5);
    }
}
