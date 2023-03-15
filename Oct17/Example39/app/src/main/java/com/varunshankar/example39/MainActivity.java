package com.varunshankar.example39;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    public TextView mTvData;
    private final double mThreshold = 2.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get sensor manager
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //get the sensor
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Get the text view
        mTvData = (TextView) findViewById(R.id.tv_data);

    }

    private SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            //Get the acceleration rates along the x and z axes
            double x = sensorEvent.values[0];
            double z = sensorEvent.values[2];

            //Check if this is greater than some threshold
            if(abs(x) > mThreshold || abs(z) > mThreshold){
                //Change color of text view to some random color
                Random rnd = new Random();
                int color = Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
                mTvData.setBackgroundColor(color);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if(mAccelerometer!=null){
            mSensorManager.registerListener(mListener,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAccelerometer!=null){
            mSensorManager.unregisterListener(mListener);
        }
    }


}
