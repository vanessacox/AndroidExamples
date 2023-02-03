package com.varunshankar.example18

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


class ViewFragment : Fragment() {
    private var mTvFirstName: TextView? = null
    private var mTvLastName: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view, container, false)

        //Get the text views
        mTvFirstName = view.findViewById<View>(R.id.tv_fn_data) as TextView
        mTvLastName = view.findViewById<View>(R.id.tv_ln_data) as TextView

        //Get the data that was sent in
        val incomingBundle = arguments
        val firstName = incomingBundle!!.getString("FN_DATA")
        val lastName = incomingBundle.getString("LN_DATA")

        //Set the data
        if (firstName != null) {
            mTvFirstName!!.text = firstName
        }
        if (lastName != null) {
            mTvLastName!!.text = lastName
        }
        return view
    }
}