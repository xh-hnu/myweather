package com.xuhe.myweather.dao;

import com.xuhe.myweather.entity.City;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CityDao {

    @Insert
    void insertCity(City... cities);

    @Update
    void updateCity(City... cities);

    @Delete
    void deleteCity(City... cities);

    @Query("SELECT * FROM CITY")
    List<City> getAllCity();
}
