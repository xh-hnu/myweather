package com.xuhe.myweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.xuhe.myweather.databinding.ListViewItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class ChooseAreaAdapter extends ArrayAdapter {

    private ListViewItemBinding binding;
    private Context context;
    private int resId;
    private List<String> dataList;

    public ChooseAreaAdapter(@NonNull Context context, int resource, @NonNull List<String> dataList) {
        super(context, resource, dataList);
        this.context = context;
        this.resId = resource;
        this.dataList = dataList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            View view = LayoutInflater.from(context).inflate(resId, parent, false);
            binding = DataBindingUtil.bind(view);
            view.setTag(binding);
            binding.setArea(dataList.get(position));
            return view;
        }else {
            binding = (ListViewItemBinding) convertView.getTag();
            binding.setArea(dataList.get(position));
            return convertView;
        }
    }
}
