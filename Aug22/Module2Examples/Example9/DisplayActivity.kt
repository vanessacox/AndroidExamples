package com.varunshankar.example9

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Toast

class DisplayActivity : AppCompatActivity() {
    private var mTvFirstName: TextView? = null
    private var mTvLastName: TextView? = null
    private var mFirstName: String? = null
    private var mLastName: String? = null
    private var mFullNameReceived: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        //Get the intent that created this activity.
        val receivedIntent = intent

        //Get the string data
        mFullNameReceived = receivedIntent.getStringExtra("ET_STRING")
        if (mFullNameReceived.isNullOrBlank()) {
            Toast.makeText(this@DisplayActivity, "Empty string received", Toast.LENGTH_SHORT).show()
        } else {
            //Remove any leading spaces or tabs
            mFullNameReceived = mFullNameReceived!!.replace("^\\s+".toRegex(), "")

            //Separate the string into first and last name using simple Java stuff
            val splitStrings = mFullNameReceived!!.split("\\s+".toRegex()).toTypedArray()
            when (splitStrings.size) {
                1 -> {
                    Toast.makeText(
                        this@DisplayActivity,
                        "Enter both first and last name!",
                        Toast.LENGTH_SHORT
                    ).show()
                    mFirstName = splitStrings[0]
                    mTvFirstName = findViewById(R.id.tv_fn_data)
                    mTvFirstName!!.text = mFirstName
                }
                2 -> {
                    mFirstName = splitStrings[0]
                    mLastName = splitStrings[1]

                    //Get the text views where we will display names
                    mTvFirstName = findViewById(R.id.tv_fn_data)
                    mTvLastName = findViewById(R.id.tv_ln_data)

                    //Set the text views
                    mTvFirstName!!.text = mFirstName
                    mTvLastName!!.text = mLastName
                }
                else -> {
                    Toast.makeText(
                        this@DisplayActivity,
                        "Enter only first and last name!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}