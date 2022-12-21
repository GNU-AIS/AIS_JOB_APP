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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;

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
        binding.eListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });
        return binding.getRoot();
    }

    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void showList() {

        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        chapterList.add("AIS 동아리");
        chapterList.add("동아리 학습목표");
        chapterList.add("앱 활동 기획");
        chapterList.add("앱 문의");
        chapterList.add("앱 정보");

        List<String> topic1 = new ArrayList<>();
        topic1.add("\nAIS는 인공지능 스터디의 줄임말입니다." +
                "\n지금 동아리 활동은 빅데이터 기술을 학습하고자" +
                "\n모였지만 추후 다시 모여 인공지능에 관해" +
                "\n더 배우자는 의지를 표명했습니다." +
                "\n\n앞으로 더 발달할 빅데이터 기술은 현재도" +
                "\n미래에도 산업의 핵심이라고 할 수 있습니다." +
                "\n\n컴퓨터공학부 학생으로서 반드시 배워야 할 것으로" +
                "\n인식하고 이번 기회로 학부 동기들이 모여" +
                "\n서로 연대하여 협동을 통해 학습하고자 합니다.\n");
        topic1.add("안드로이드 멤버 : 김주영 이동현");
        topic1.add("웹크롤링 멤버 : 박지홍 차도훈");
        topic1.add("서버 멤버 : 김민기 최해영");

        List<String> topic11 = new ArrayList<>();
        topic11.add("\n빅데이터 관련 도서를 활용하여 빅데이터 기술을" +
                "\n동아리 구성원들이 모여 서로 발표하고 토론을 통해 학습합니다." +
                "" +
                "\n\n도서의 예제 프로젝트를 실습하고 최종적으로" +
                "\n인터넷에 공개된 데이터를 빅데이터 기술로" +
                "\n수집, 분류 하는 시스템을 개발하는" +
                "\n프로젝트를 진행합니다.\n");

        List<String> topic2 = new ArrayList<>();
        topic2.add("1주차 : 4차 산업과 빅데이터에 대한 개념 학습");
        topic2.add("2주차 : 데이터 과학 기반의 데이터 분석 학습");
        topic2.add("3주차 : 파이썬 크롤링 API 개념과 사용법 학습");
        topic2.add("4주차 : 통계 분석 (와인 품질 등급 예측) 학습");
        topic2.add("5주차 : 텍스트 빈도 분석 (기사 제목의 키워드) 학습");
        topic2.add("6주차 : 지리 정보 분석 (행정 구역별 의료기관) 학습");
        topic2.add("7주차 : 회귀 분석 (환경에 따른 주택 가격 예측) 학습");
        topic2.add("8주차 : 분류 분석 (유방암 진단) 학습");
        topic2.add("9주차 : 프로젝트 계획, 기획 및 동작 설계");
        topic2.add("10주차 : 프로그램 UI 구현 및 제작");
        topic2.add("11주차 : 목적에 맞는 API 선별 및 크롤링 설계");
        topic2.add("12주차 : 프로젝트 중간 점검 및 피드백");
        topic2.add("13주차 : 앱 UI와 크롤링 API 연동");
        topic2.add("14주차 : 서버와 데이터베이스 구축 및 연동");
        topic2.add("15주차 : 서버, API, 크롤링, 앱 연동");
        topic2.add("16주차 : 실사 테스트 진행 및 피드백");
        topic2.add("17주차 : 프로젝트 최종 수정 및 PPT 제작");


        List<String> topic3 = new ArrayList<>();
        topic3.add("문의하기");


        List<String> topic4 = new ArrayList<>();
        topic4.add("컴퓨터공학부 학생들의 진로를 위해" +
                "\n개발자 관련 채용 공고를 제공하며" +
                "\n사용 허가받은 공개된 정보만 제공합니다." +
                "\n\nAndroid Studio\nPyCharm\nMySQL 8.0\nOpenAPI WorkNet");



        topicList.put(chapterList.get(0),topic1);
        topicList.put(chapterList.get(1),topic11);
        topicList.put(chapterList.get(2),topic2);
        topicList.put(chapterList.get(3),topic3);
        topicList.put(chapterList.get(4),topic4);
    }
}
