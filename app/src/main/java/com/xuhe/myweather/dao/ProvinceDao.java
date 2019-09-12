package com.xuhe.myweather.dao;

import com.xuhe.myweather.entity.Province;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProvinceDao {

    @Insert
    void insertProvince(Province... provinces);

    @Delete
    void deleteProvince(Province... provinces);

    @Update
    void updateProvince(Province... provinces);

    @Query("SELECT * FROM PROVINCE")
    List<Province> getAllProvince();
}
