package com.varunshankar.example20

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get the recycler view
        mRecyclerView = findViewById<View>(R.id.rv_Master) as RecyclerView

        //Tell Android that we know the size of the recyclerview
        //doesn't change
        mRecyclerView!!.setHasFixedSize(true)

        //Set the layout manager
        layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = layoutManager

        //Populate the list with data
        val inputList: MutableList<String> = ArrayList()
        for (i in 1..100000) {
            inputList.add("Item $i")
        }

        //Set the adapter
        mAdapter = MyRVAdapter(inputList)
        mRecyclerView!!.adapter = mAdapter
    }
}