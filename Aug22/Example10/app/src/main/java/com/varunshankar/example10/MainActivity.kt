package com.varunshankar.example10

import android.content.ActivityNotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mButtonSubmit: Button? = null
    private var mEtSearchString: EditText? = null
    private var mSearchString: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get the button
        mButtonSubmit = findViewById(R.id.button_submit)
        mButtonSubmit!!.setOnClickListener(this)


        //Get the EditText
        mEtSearchString = findViewById(R.id.et_search)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_submit -> {

                //Get the string from the EditText
                mSearchString = mEtSearchString!!.text.toString()
                if (mSearchString.isNullOrBlank()) {
                    Toast.makeText(this, "Enter something!", Toast.LENGTH_SHORT).show()
                } else {
                    //We have to grab the search term and construct a URI object from it.
                    //We'll hardcode WEB's location here
                    val searchUri = Uri.parse("geo:40.767778,-111.845205?q=$mSearchString")

                    //Create the implicit intent
                    val mapIntent = Intent(Intent.ACTION_VIEW, searchUri)

                    //If there's an activity associated with this intent, launch it
                    try{
                        startActivity(mapIntent)
                    }catch(ex: ActivityNotFoundException){
                        //handle errors here
                    }
                }
            }
        }
    }
}