package com.varunshankar.example17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find each frame layout, replace with corresponding fragment
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.fl_frag_ph_1, Fragment1(), "Frag_1")
        fTrans.replace(R.id.fl_frag_ph_2, Fragment2(), "Frag 2")
        fTrans.commit()
    }
}