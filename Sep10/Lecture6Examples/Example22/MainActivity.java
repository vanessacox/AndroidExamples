package com.varunshankar.example22;


import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create and populate our custom list
        CustomListData customListData = new CustomListData(1000);

        //Put this into a bundle
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putParcelable("item_list",customListData);

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
