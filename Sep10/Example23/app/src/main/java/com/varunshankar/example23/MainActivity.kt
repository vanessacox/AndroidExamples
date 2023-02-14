package com.varunshankar.example23

import androidx.appcompat.app.AppCompatActivity
import com.varunshankar.example23.MyRVAdapter.DataPasser
import android.os.Bundle


class MainActivity : AppCompatActivity(), DataPasser {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Put this into a bundle
        val fragmentBundle = Bundle()
        fragmentBundle.putParcelable("item_list", mCustomListData)

        //Create the fragment
        val masterListFragment = MasterListFragment()

        //Pass data to the fragment
        masterListFragment.arguments = fragmentBundle

        //Replace the fragment container
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.fl_frag_masterlist, masterListFragment, "frag_masterList")
        fTrans.commit()
    }

    //This receives the position of the clicked item in the MasterListFragment's RecyclerView
    override fun passData(position: Int) {
        //Get the string data corresponding to the detail view
        val itemDetail = mCustomListData.getItemDetail(position)

        //Put this into a bundle
        val detailFragmentBundle = Bundle()
        detailFragmentBundle.putString("item_detail", itemDetail)

        //Create a new detail fragment
        val itemDetailFragment = ItemDetailFragment()

        //Pass data to the fragment
        itemDetailFragment.arguments = detailFragmentBundle

        //Replace the detail fragment container
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.fl_frag_itemdetail, itemDetailFragment, "frag_itemdetail")
        fTrans.commit()
    }

    companion object {
        private val mCustomListData = CustomListData(1000)
    }
}