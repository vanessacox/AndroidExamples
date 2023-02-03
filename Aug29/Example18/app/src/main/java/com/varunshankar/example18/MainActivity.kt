package com.varunshankar.example18

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mStringFullName: String? = null
    private var mEtFullName: EditText? = null
    private var mBtSubmit: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get stuff
        mEtFullName = findViewById<View>(R.id.et_name) as EditText
        mBtSubmit = findViewById<View>(R.id.button_submit) as Button
        mBtSubmit!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_submit -> {
                mStringFullName = mEtFullName!!.text.toString()

                //Check if the EditText string is empty
                if (mStringFullName.isNullOrBlank()) {
                    //Complain that there's no text
                    Toast.makeText(this@MainActivity, "Enter a name first!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    //Reward them for submitting their names
                    Toast.makeText(this@MainActivity, "Good job!", Toast.LENGTH_SHORT).show()

                    //Remove any leading spaces or tabs
                    mStringFullName = mStringFullName!!.replace("^\\s+".toRegex(), "")

                    //Separate the string into first and last name using simple Java stuff
                    val splitStrings = mStringFullName!!.split("\\s+".toRegex()).toTypedArray()
                    when (splitStrings.size) {
                        1 -> {
                            Toast.makeText(
                                this@MainActivity,
                                "Enter both first and last name!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        2 -> {
                            val firstName = splitStrings[0]
                            val lastName = splitStrings[1]

                            //Instantiate the fragment
                            val viewFragment = ViewFragment()

                            //Send data to it
                            val sentData = Bundle()
                            sentData.putString("FN_DATA", firstName)
                            sentData.putString("LN_DATA", lastName)
                            viewFragment.arguments = sentData

                            //Replace the fragment container
                            val fTrans = supportFragmentManager.beginTransaction()
                            fTrans.replace(R.id.fl_frag_container, viewFragment, "view_frag")
                            fTrans.commit()
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