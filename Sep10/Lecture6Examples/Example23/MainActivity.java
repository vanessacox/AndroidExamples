package com.varunshankar.example23;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
                            implements MyRVAdapter.DataPasser{

    private static final CustomListData mCustomListData = new CustomListData(1000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Put this into a bundle
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putParcelable("item_list",mCustomListData);

        //Create the fragment
        MasterListFragment masterListFragment = new MasterListFragment();

        //Pass data to the fragment
        masterListFragment.setArguments(fragmentBundle);

        //Replace the fragment container
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fl_frag_masterlist, masterListFragment, "frag_masterList");
        fTrans.commit();
    }

    //This receives the position of the clicked item in the MasterListFragment's RecyclerView
    @Override
    public void passData(int position) {
        //Get the string data corresponding to the detail view
        String itemDetail = mCustomListData.getItemDetail(position);

        //Put this into a bundle
        Bundle detailFragmentBundle = new Bundle();
        detailFragmentBundle.putString("item_detail",itemDetail);

        //Create a new detail fragment
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();

        //Pass data to the fragment
        itemDetailFragment.setArguments(detailFragmentBundle);

        //Replace the detail fragment container
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fl_frag_itemdetail,itemDetailFragment,"frag_itemdetail");
        fTrans.commit();

    }
}