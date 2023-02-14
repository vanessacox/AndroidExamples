package com.varunshankar.example21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Populate the list with data
        val inputList = ArrayList<String>()
        for (i in 1..100) {
            inputList.add("Item $i")
        }

        //Put this into a bundle
        val fragmentBundle = Bundle()
        fragmentBundle.putStringArrayList("item_list", inputList)

        //Create the fragment
        val masterListFragment = MasterListFragment()

        //Pass data to the fragment
        masterListFragment.arguments = fragmentBundle

        //Replace the fragment container
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.fl_frag_masterlist, masterListFragment, "frag_masterList")
        fTrans.commit()
    }
}