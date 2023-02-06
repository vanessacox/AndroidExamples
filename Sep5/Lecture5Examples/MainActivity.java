package com.varunshankar.example20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_Master);

        //Tell Android that we know the size of the recyclerview
        //doesn't change
        mRecyclerView.setHasFixedSize(true);

        //Set the layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //Populate the list with data
        List<String> inputList = new ArrayList<>();
        for (int i=1;i<=100;i++){
            inputList.add("Item " + i);
        }

        //Set the adapter
        mAdapter = new MyRVAdapter(inputList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
