package com.xuhe.myweather.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.xuhe.myweather.R;
import com.xuhe.myweather.adapter.ChooseAreaAdapter;
import com.xuhe.myweather.databinding.ChooseAreaBinding;
import com.xuhe.myweather.viewmodel.ChooseAreaVM;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class ChooseAreaFragment extends Fragment {

    private ChooseAreaVM chooseAreaVM;
    private ProgressDialog progressDialog;
    private ArrayAdapter<String> adapter;
    private ChooseAreaBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.choose_area, container, false);
       binding = DataBindingUtil.bind(view);
       chooseAreaVM = new ChooseAreaVM(getActivity().getApplication());
       binding.setData(chooseAreaVM);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ChooseAreaAdapter(getContext(), R.layout.list_view_item, chooseAreaVM.getDataList());
        binding.listView.setAdapter(adapter);
        observe();
    }

    private void observe() {
        chooseAreaVM.getCurrentLevel().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case ChooseAreaVM.LEVEL_PROVINCE:
                        binding.titleText.setText("中国");
                        binding.titleBack.setVisibility(View.GONE);
                        break;
                    case ChooseAreaVM.LEVEL_CITY:
                        binding.titleText.setText("");
                        binding.titleBack.setVisibility(View.VISIBLE);
                        break;
                    case ChooseAreaVM.LEVEL_COUNTY:
                        break;
                }
            }
        });
        if (chooseAreaVM.getDataList().isEmpty()){
            chooseAreaVM.getProvinces();
        }
    }

}
