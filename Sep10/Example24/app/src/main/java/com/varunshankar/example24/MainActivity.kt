package com.varunshankar.example24

import androidx.appcompat.app.AppCompatActivity
import com.varunshankar.example24.MyRVAdapter.DataPasser
import android.os.Bundle
import android.content.Intent


class MainActivity : AppCompatActivity(), DataPasser {
    private var mMasterListFragment: MasterListFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Put this into a bundle
        val fragmentBundle = Bundle()
        fragmentBundle.putParcelable("item_list", mCustomListData)

        //Create the fragment
        mMasterListFragment = MasterListFragment()


        //Pass data to the fragment
        mMasterListFragment!!.arguments = fragmentBundle

        //If we're on a tablet, the master fragment appears on the left pane. If we're on a phone,
        //it takes over the whole screen
        val fTrans = supportFragmentManager.beginTransaction()
        if (isTablet) {
            //Pane 1: Master list
            fTrans.replace(
                R.id.fl_frag_masterlist_container_tablet,
                mMasterListFragment!!,
                "frag_masterlist"
            )
        } else {
            fTrans.replace(
                R.id.fl_frag_masterlist_container_phone,
                mMasterListFragment!!,
                "frag_masterlist"
            )
        }
        fTrans.commit()
    }

    //This receives the position of the clicked item in the MasterListFragment's RecyclerView
    override fun passData(position: Int) {
        //Get the string data corresponding to the detail view
        val itemDetailString = mCustomListData.getItemDetail(position)

        //Put this into a bundle
        val detailBundle = Bundle()
        detailBundle.putString("item_detail", itemDetailString)

        //If we're on a tablet, the fragment occupies the second pane (right). If we're on a phone,
        //the fragment is on a new Activity
        if (isTablet) {
            //Create a new detail fragment
            val itemDetailFragment = ItemDetailFragment()

            //Pass data to the fragment
            itemDetailFragment.arguments = detailBundle

            //Replace the detail fragment container
            val fTrans = supportFragmentManager.beginTransaction()
            fTrans.replace(
                R.id.fl_frag_itemdetail_container_tablet,
                itemDetailFragment,
                "frag_itemdetail"
            )
            fTrans.commit()
        } else { //On a phone
            //Start ItemDetailActivity, pass the string along
            val sendIntent = Intent(this, ItemDetailActivity::class.java)
            sendIntent.putExtras(detailBundle)
            startActivity(sendIntent)
        }
    }

    private val isTablet: Boolean
        get() = resources.getBoolean(R.bool.isTablet)

    companion object {
        private val mCustomListData = CustomListData(1000)
    }
}