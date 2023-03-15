package com.varunshankar.example35;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class WeatherRepository {
    private final MutableLiveData<WeatherData> jsonData =
            new MutableLiveData<WeatherData>();
    private String mLocation;
    private String mJsonString;
    private WeatherDao mWeatherDao;

    WeatherRepository(Application application){
        WeatherRoomDatabase db = WeatherRoomDatabase.getDatabase(application);
        mWeatherDao = db.weatherDao();
        loadData();
    }

    public void setLocation(String location){
        mLocation = location;
        loadData();
    }

    public MutableLiveData<WeatherData> getData() {
        return jsonData;
    }

    private void loadData(){
        new AsyncTask<String,Void,String>(){
            @Override
            protected String doInBackground(String... strings) {
                String location = strings[0];
                URL weatherDataURL = null;
                String retrievedJsonData = null;
                if(location!=null) {
                    weatherDataURL = NetworkUtils.buildURLFromString(location);
                    try {
                        retrievedJsonData = NetworkUtils.getDataFromURL(weatherDataURL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return retrievedJsonData;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s!=null) {
                    mJsonString = s;
                    insert();
                    try {
                        jsonData.setValue(JSONWeatherUtils.getWeatherData(s));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.execute(mLocation);

    }

    private void insert(){
        WeatherTable weatherTable = new WeatherTable(mLocation,mJsonString);
        new insertAsyncTask(mWeatherDao).execute(weatherTable);
    }

    private static class insertAsyncTask extends AsyncTask<WeatherTable,Void,Void>{
        private WeatherDao mAsyncTaskDao;

        insertAsyncTask(WeatherDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(WeatherTable... weatherTables) {
            mAsyncTaskDao.insert(weatherTables[0]);
            return null;
        }
    }
}
