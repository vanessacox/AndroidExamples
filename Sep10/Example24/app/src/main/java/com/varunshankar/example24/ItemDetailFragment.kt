package com.varunshankar.example24

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class ItemDetailFragment : Fragment(), View.OnClickListener {
    private var mTvItemDetail: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the detail view
        val view = inflater.inflate(R.layout.fragment_itemdetail_layout, container, false)

        //Get the text view
        mTvItemDetail = view.findViewById<View>(R.id.tv_detail) as TextView

        //Get the incoming detail text
        val detailString = requireArguments().getString("item_detail")
        if (detailString != null) {
            mTvItemDetail!!.text = detailString
        }
        return view
    }

    override fun onClick(view: View) {}
}