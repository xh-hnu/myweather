package com.xuhe.myweather.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.xuhe.myweather.dao.CityDao;
import com.xuhe.myweather.dao.CountyDao;
import com.xuhe.myweather.dao.ProvinceDao;
import com.xuhe.myweather.database.MyDatabase;
import com.xuhe.myweather.entity.City;
import com.xuhe.myweather.entity.County;
import com.xuhe.myweather.entity.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    private static MyDatabase database;
    private static ProvinceDao provinceDao;
    private static CityDao cityDao;
    private static CountyDao countyDao;

    private static Utility instance;

    public static Utility getInstance(Context context) {
        if (instance == null){
            instance = new Utility(context);
        }
        return instance;
    }

    public Utility(Context context) {
        database = MyDatabase.getInstance(context);
        provinceDao = database.getProvinceDao();
        cityDao = database.getCityDao();
        countyDao = database.getCountyDao();
    }

    /**
     * 解析处理服务器返回的省级数据
     */
    public boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++){
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    provinceDao.insertProvince(province);
                    Log.d("province insert", "success");
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("province insert", "failure");
            }
        }
        return false;
    }

    /**
     * 解析处理服务器返回的市级数据
     */
    public boolean handleCityResponse(String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++){
                    JSONObject cityObj = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    cityDao.insertCity(city);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析处理服务器返回的县级数据
     */

    public boolean handleCountyResponse(String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++){
                    JSONObject countyObj = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObj.getString("name"));
                    county.setWeatherId(countyObj.getString("weather_id"));
                    county.setCityId(cityId);
                    countyDao.insertCounty(county);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
