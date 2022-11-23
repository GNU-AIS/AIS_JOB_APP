package com.example.ais_job_app.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ais_job_app.CorpReqInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<CorpReqInfo> corpReqInfos = new ArrayList<>();

    private CorpReqInfoAdapter corpReqInfoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        /* 서치뷰 */
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    corpReqInfoAdapter.setListItems(corpReqInfos);
                }
                return true;
            }
        });

        return binding.getRoot();
    }

    private void filterList(String str){
        // 검색 결과에 맞는 리스트만 만들기 위한 리스트를 만듬
        ArrayList<CorpReqInfo> arrayList = new ArrayList<>();
        for(CorpReqInfo corpReqInfo : corpReqInfos){
            if(corpReqInfo.getName().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            } else if (corpReqInfo.getDay().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }else if (corpReqInfo.getTitle().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }else if (corpReqInfo.getCareer().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }else if (corpReqInfo.getEducation().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }else if (corpReqInfo.getPreference().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }else if (corpReqInfo.getPattern().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }else if (corpReqInfo.getSalary().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }else if (corpReqInfo.getArea().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }else if (corpReqInfo.getTitle().toLowerCase().contains((str.toLowerCase()))){
                arrayList.add(corpReqInfo);
            }
        }

        if(arrayList.isEmpty()){
            Toast.makeText(requireContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
        } else{
            corpReqInfoAdapter.setListItems(arrayList);
        }

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvIsEmpty2.setText("표시할 수 있는 채용공고가 없어요");
        binding.tvIsEmpty.setText("텅...");
        if(corpReqInfos.isEmpty()){
            binding.tvIsEmpty.setVisibility(View.GONE);
            binding.tvIsEmpty2.setVisibility(View.GONE);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(), R.array.myRegion, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //드롭다운뷰 연결
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //UI와 연결
        binding.spRegion.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(requireActivity(), R.array.myField, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //드롭다운뷰 연결
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //UI와 연결
        binding.spField.setAdapter(adapter2);


        /**/

        corpReqInfos.add(new CorpReqInfo("삼성", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));
        corpReqInfos.add(new CorpReqInfo("엘쥐", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));
        corpReqInfos.add(new CorpReqInfo("skt", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));
        corpReqInfos.add(new CorpReqInfo("kt", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));
        corpReqInfos.add(new CorpReqInfo("경상국립대학교", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));


        corpReqInfoAdapter = new CorpReqInfoAdapter(corpReqInfos, requireContext());

        binding.rv.setHasFixedSize(true);

        binding.rv.setAdapter(corpReqInfoAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}