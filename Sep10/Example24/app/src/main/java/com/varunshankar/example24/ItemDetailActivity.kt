package com.varunshankar.example24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class ItemDetailActivity : AppCompatActivity() {
    private var mItemDetailFragment: ItemDetailFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        //Create the fragment
        mItemDetailFragment = ItemDetailFragment()

        //Pass data to the fragment
        mItemDetailFragment!!.arguments = intent.extras

        //No need to check if we're on a tablet. This activity only gets created on phones.
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(
            R.id.fl_frag_itemdetail_container_phone,
            mItemDetailFragment!!,
            "frag_itemdetail"
        )
        fTrans.commit()
    }
}