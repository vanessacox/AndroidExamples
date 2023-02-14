package com.varunshankar.example22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create and populate our custom list
        val customListData = CustomListData(1000)

        //Put this into a bundle
        val fragmentBundle = Bundle()
        fragmentBundle.putParcelable("item_list", customListData)

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