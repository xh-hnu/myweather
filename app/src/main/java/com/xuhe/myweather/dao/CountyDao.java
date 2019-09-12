package com.xuhe.myweather.dao;

import com.xuhe.myweather.entity.County;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CountyDao {

    @Insert
    void insertCounty(County... counties);

    @Update
    void updateCounty(County... counties);

    @Delete
    void deleteCounty(County... counties);

    @Query("SELECT * FROM COUNTY")
    List<County> getAllCounty();
}
