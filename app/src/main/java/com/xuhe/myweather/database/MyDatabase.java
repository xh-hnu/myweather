package com.xuhe.myweather.database;

import android.content.Context;

import com.xuhe.myweather.dao.CityDao;
import com.xuhe.myweather.dao.CountyDao;
import com.xuhe.myweather.dao.ProvinceDao;
import com.xuhe.myweather.entity.City;
import com.xuhe.myweather.entity.County;
import com.xuhe.myweather.entity.Province;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {City.class, Province.class, County.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static MyDatabase instance = null;

    public static MyDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "room_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract CityDao getCityDao();
    public abstract ProvinceDao getProvinceDao();
    public abstract CountyDao getCountyDao();
}
