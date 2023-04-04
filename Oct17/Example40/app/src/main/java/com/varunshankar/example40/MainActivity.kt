package com.varunshankar.example40

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    var mTvData: TextView? = null
    private val mThreshold = 2.0

    //Previous accelerations
    private var last_x = 0.0
    private var last_y = 0.0
    private var last_z = 0.0

    //current accelerations
    private var now_x = 0.0
    private var now_y = 0.0
    private var now_z = 0.0
    private var mNotFirstTime = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get sensor manager
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        //Get the default accelerometer
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        //Get the text view
        mTvData = findViewById(R.id.tv_data)
    }

    private val mListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {

            //Get the accelerations
            now_x = sensorEvent.values.get(0).toDouble()
            now_y = sensorEvent.values.get(1).toDouble()
            now_z = sensorEvent.values.get(2).toDouble()
            if (mNotFirstTime) {
                val dx = Math.abs(last_x - now_x)
                val dy = Math.abs(last_y - now_y)
                val dz = Math.abs(last_z - now_z)

                //Check if the values of acceleration have changed on any pair of axes
                if ((dx > mThreshold && dy > mThreshold) || (dx > mThreshold && dz > mThreshold) || (dy > mThreshold && dz > mThreshold)) {

                    //Change color of text view to some random color
                    val rnd = Random()
                    val color =
                        Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                    mTvData!!.setBackgroundColor(color)
                }
            }
            last_x = now_x
            last_y = now_y
            last_z = now_z
            mNotFirstTime = true
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
    }

    override fun onResume() {
        super.onResume()
        if (mAccelerometer != null) {
            mSensorManager!!.registerListener(
                mListener,
                mAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override protected fun onPause() {
        super.onPause()
        if (mAccelerometer != null) {
            mSensorManager!!.unregisterListener(mListener)
        }
    }
}