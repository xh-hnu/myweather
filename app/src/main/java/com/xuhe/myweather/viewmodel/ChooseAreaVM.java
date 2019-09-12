package com.xuhe.myweather.viewmodel;

import android.app.Application;

import com.xuhe.myweather.dao.CityDao;
import com.xuhe.myweather.dao.CountyDao;
import com.xuhe.myweather.dao.ProvinceDao;
import com.xuhe.myweather.database.MyDatabase;
import com.xuhe.myweather.entity.Province;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ChooseAreaVM extends AndroidViewModel {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    private MyDatabase database;
    private ProvinceDao provinceDao;
    private CityDao cityDao;
    private CountyDao countyDao;

    private MutableLiveData<Integer> currentLevel;
    private MutableLiveData<List<Province>> provinceList;

    private List<String> dataList;

    public ChooseAreaVM(@NonNull Application application) {
        super(application);

        database = MyDatabase.getInstance(application);
        provinceDao = database.getProvinceDao();
        cityDao = database.getCityDao();
        countyDao = database.getCountyDao();

        currentLevel = new MutableLiveData<>();
        provinceList = new MutableLiveData<>();
        dataList = new ArrayList<>();
    }

    public List<String> getProvinces(){
        dataList.clear();
        currentLevel.setValue(LEVEL_PROVINCE);
        provinceList.setValue(provinceDao.getAllProvince());
        if (provinceList.getValue().size() > 0){
            for (Province province : provinceList.getValue()){
                dataList.add(province.getProvinceName());
            }
        }else{
            String url = "http://guolin.tech/api/china";
            loadFromService(url, "province");
        }
        return dataList;
    }

    private void loadFromService(String url, String type) {

    }

    public void onBack(){
        if (currentLevel.getValue().equals(LEVEL_CITY)){
            getProvinces();
        }
    }


    public MutableLiveData<Integer> getCurrentLevel() {
        return currentLevel;
    }

    public List<String> getDataList() {
        return dataList;
    }
}
