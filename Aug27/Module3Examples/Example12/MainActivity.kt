package com.varunshankar.example12

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
    private val mTvFullName: TextView? = null
    private var mStringFullName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButtonSubmit = findViewById(R.id.button_submit)
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
}