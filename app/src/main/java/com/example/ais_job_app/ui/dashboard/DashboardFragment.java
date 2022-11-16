package com.example.ais_job_app.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ais_job_app.CorpReqInfo;
import com.example.ais_job_app.R;
import com.example.ais_job_app.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<CorpReqInfo> corpReqInfos = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
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
        corpReqInfos.add(new CorpReqInfo("삼성", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));
        corpReqInfos.add(new CorpReqInfo("삼성", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));
        corpReqInfos.add(new CorpReqInfo("삼성", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));
        corpReqInfos.add(new CorpReqInfo("삼성", "이렇게 좋은 기업에 올사람 구합니다.", "초보가능", "고졸가능", "군필우대", "정규직", "연봉3000", "경상도가능", "주5", ""));




        binding.rv.setHasFixedSize(true);

        CorpReqInfoAdapter corpReqInfoAdapter = new CorpReqInfoAdapter(corpReqInfos, requireContext());
        binding.rv.setAdapter(corpReqInfoAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}