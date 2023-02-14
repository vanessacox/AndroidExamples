package com.varunshankar.example22;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MasterListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public MasterListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_masterlist_layout,container,false);

        //Get the recycler view
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_Master);

        //Tell Android that we know the size of the recyclerview doesn't change
        mRecyclerView.setHasFixedSize(true);

        //Set the layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        //Get data from main activity
        CustomListData customListData = getArguments().getParcelable("item_list");
        List<String> inputList = customListData.getItemList();

        //Set the adapter
        mAdapter = new MyRVAdapter(inputList);
        mRecyclerView.setAdapter(mAdapter);

        return fragmentView;
    }

}
