package com.varunshankar.example19

import androidx.appcompat.app.AppCompatActivity
import com.varunshankar.example19.SubmitFragment.DataPassingInterface
import android.widget.TextView
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity(), DataPassingInterface {
    private var mStringFirstName: String? = null
    private var mStringLastName: String? = null
    private var mTvFirstName: TextView? = null
    private var mTvLastName: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get stuff
        mTvFirstName = findViewById<View>(R.id.tv_fn_data) as TextView
        mTvLastName = findViewById<View>(R.id.tv_ln_data) as TextView

        //Create the fragment
        val submitFragment = SubmitFragment()

        //Replace the fragment container
        val fTrans = supportFragmentManager.beginTransaction()
        fTrans.replace(R.id.fl_frag_container, submitFragment, "submit_frag")
        fTrans.commit()
    }

    override fun passData(data: Array<String?>?) {
        mStringFirstName = data!![0]
        mStringLastName = data[1]
        if (mStringFirstName != null) {
            mTvFirstName!!.text = mStringFirstName
        }
        if (mTvLastName != null) {
            mTvLastName!!.text = mStringLastName
        }
    }
}