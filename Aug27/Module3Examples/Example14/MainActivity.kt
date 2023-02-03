package com.varunshankar.example14

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mButtonSubmit: Button? = null
    private var mEtFullName: EditText? = null
    private var mTvFirstName: TextView? = null
    private var mTvLastName: TextView? = null
    private var mStringFullName: String? = null
    private var mStringFirstName: String? = null
    private var mStringLastName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButtonSubmit = findViewById<View>(R.id.button_submit) as Button
        mButtonSubmit!!.setOnClickListener(this)
        mEtFullName = findViewById<View>(R.id.et_name) as EditText
        mTvFirstName = findViewById<View>(R.id.tv_fn_display) as TextView
        mTvLastName = findViewById<View>(R.id.tv_ln_display) as TextView
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_submit -> {
                mStringFullName = mEtFullName!!.text.toString()
                if (mStringFullName.isNullOrBlank()) {
                    Toast.makeText(this, "Enter something", Toast.LENGTH_SHORT).show()
                } else {
                    val splitStrings = mStringFullName!!.split("\\s+".toRegex()).toTypedArray()
                    if (splitStrings.size == 1) {
                        Toast.makeText(this, "Enter both first and last name", Toast.LENGTH_SHORT)
                            .show()
                        mTvFirstName!!.text = splitStrings[0]
                    } else {
                        mTvFirstName!!.text = splitStrings[0]
                        mTvLastName!!.text = splitStrings[1]
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        //Save the view hierarchy
        super.onSaveInstanceState(outState)

        //Get the strings
        mStringFirstName = mTvFirstName!!.text.toString()
        mStringLastName = mTvLastName!!.text.toString()

        //Put them in the outgoing Bundle
        outState.putString("FN_TEXT", mStringFirstName)
        outState.putString("LN_TEXT", mStringLastName)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        //Restore the view hierarchy automatically
        super.onRestoreInstanceState(savedInstanceState)

        //Restore stuff
        mTvFirstName!!.text = savedInstanceState.getString("FN_TEXT")
        mTvLastName!!.text = savedInstanceState.getString("LN_TEXT")
    }
}