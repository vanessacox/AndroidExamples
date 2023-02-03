package com.varunshankar.example8

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.EditText
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

//Implement View.onClickListener to listen to button clicks. This means we have to override onClick().
class MainActivity : AppCompatActivity(), View.OnClickListener {
    //Create variables to hold the three strings
    private var mFullName: String? = null
    private var mFirstName: String? = null
    private var mLastName: String? = null

    //Create variables for the UI elements that we need to control
    private var mTvFirstName: TextView? = null
    private var mTvLastName: TextView? = null
    private var mButtonSubmit: Button? = null
    private var mEtFullName: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get the text views where we will display names
        mTvFirstName = findViewById(R.id.tv_fn_data)
        mTvLastName = findViewById(R.id.tv_ln_data)

        //Get the button
        mButtonSubmit = findViewById(R.id.button_submit)

        //Say that this class itself contains the listener.
        // Kotlin note: !! means the function below will
        // execute only if mButtonSubmit is not null.
        // if it is null, it will throw a null pointer exception
        // Only use !! if you are 100% sure that the variable is not null!
        mButtonSubmit!!.setOnClickListener(this)
    }

    //Handle clicks for ALL buttons here
    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_submit -> {

                //0: clear our TextViews
                mTvFirstName!!.text = ""
                mTvLastName!!.text = ""

                //First, get the string from the EditText
                mEtFullName = findViewById(R.id.et_name)
                mFullName = mEtFullName!!.text.toString()

                //Check if the EditText string is empty
                if (mFullName.isNullOrBlank()) {
                    //Complain that there's no text
                    Toast.makeText(this@MainActivity, "Enter a name first!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    //Reward them for submitting their names
                    Toast.makeText(this@MainActivity, "Good job!", Toast.LENGTH_SHORT).show()

                    //Remove any leading spaces or tabs
                    mFullName = mFullName!!.replace("^\\s+".toRegex(), "")

                    //Separate the string into first and last name using simple Java stuff
                    val splitStrings = mFullName!!.split("\\s+".toRegex()).toTypedArray()
                    when (splitStrings.size) {
                        1 -> {
                            Toast.makeText(
                                this@MainActivity,
                                "Enter both first and last name!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        2 -> {
                            mFirstName = splitStrings[0]
                            mLastName = splitStrings[1]

                            //Set the text views
                            mTvFirstName!!.text = mFirstName
                            mTvLastName!!.text = mLastName
                        }
                        else -> {
                            Toast.makeText(
                                this@MainActivity,
                                "Enter only first and last name!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}