package com.varunshankar.example38;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

    public class MainActivity extends AppCompatActivity {
        private SensorManager mSensorManager;
        private Sensor mGyroscope;
        public TextView mTvData;
        private final double mThreshold = 0.2;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get sensor manager
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Get the default gyroscope sensor
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //Get the text view
        mTvData = (TextView) findViewById(R.id.tv_data);

    }

    private SensorEventListener mListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            //Get the rotation rates along the x,y and z axes
            double xrot = sensorEvent.values[0];
            double yrot = sensorEvent.values[1];
            double zrot = sensorEvent.values[2];

            //Compute the magnitude of the rotation
            double sq_magnitude = (Math.pow(xrot,2) + Math.pow(yrot,2) + Math.pow(zrot,2));
            double magnitude = Math.max(Math.max(Math.abs(xrot),Math.abs(yrot)), Math.abs(zrot));

            //Check if this is greater than some threshold
            if(sq_magnitude > mThreshold){
                //Change color of text view to some random color
                Random rnd = new Random();
                int color = Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
                mTvData.setBackgroundColor(color);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            Toast.makeText(getApplicationContext(),"Accuracy Changed", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if(mGyroscope!=null){
            mSensorManager.registerListener(mListener,mGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mGyroscope!=null){
            mSensorManager.unregisterListener(mListener);
        }
    }


}
