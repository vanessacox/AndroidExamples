package com.varunshankar.example21;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Populate the list with data
        ArrayList<String> inputList = new ArrayList<>();
        for (int i=1;i<=100;i++){
            inputList.add("Item " + i);
        }

        //Put this into a bundle
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putStringArrayList("item_list",inputList);

        //Create the fragment
        MasterListFragment masterListFragment = new MasterListFragment();

        //Pass data to the fragment
        masterListFragment.setArguments(fragmentBundle);

        //Replace the fragment container
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fl_frag_masterlist, masterListFragment, "frag_masterList");
        fTrans.commit();

    }
}
