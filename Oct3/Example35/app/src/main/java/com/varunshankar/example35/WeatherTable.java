package com.varunshankar.example35;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "weather_table")
public class WeatherTable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "location")
    private String location;

    @NonNull
    @ColumnInfo(name = "weatherdata")
    private String weatherJson;

    public WeatherTable(@NonNull String location, @NonNull String weatherJson){
        this.location = location;
        this.weatherJson = weatherJson;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setWeatherJson(String weatherdata){
        this.weatherJson = weatherdata;
    }

    public String getLocation(){
        return location;
    }

    public String getWeatherJson(){
        return weatherJson;
    }
}
