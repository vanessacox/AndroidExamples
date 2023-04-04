package com.varunshankar.example41

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mSensorManager: SensorManager? = null
    private var mTvData: TextView? = null
    private var mStepCounter: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTvData = findViewById(R.id.tv_data) as TextView?
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?
        mStepCounter = mSensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }

    private val mListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            mTvData!!.text = "" + sensorEvent.values[0].toString()
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
    }

    override fun onResume() {
        super.onResume()
        if (mStepCounter != null) {
            mSensorManager!!.registerListener(
                mListener,
                mStepCounter,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onPause() {
        super.onPause()
        if (mStepCounter != null) {
            mSensorManager!!.unregisterListener(mListener)
        }
    }
}