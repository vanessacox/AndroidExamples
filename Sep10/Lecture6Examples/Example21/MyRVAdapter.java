package com.varunshankar.example21;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.ViewHolder> {
    private ArrayList<String> mListItems;
    private Context mContext;

    public MyRVAdapter(ArrayList<String> inputList) {

        mListItems = inputList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        protected View itemLayout;
        protected TextView itemTvData;

        public ViewHolder(View view){
            super(view);
            itemLayout = view;
            itemTvData = (TextView) view.findViewById(R.id.tv_data);
        }
    }

    @NonNull
    @Override
    public MyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View myView = layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.itemTvData.setText(mListItems.get(position));
        holder.itemLayout.setOnClickListener(new View.OnClickListener(){
                                                 @Override
                                                 public void onClick(View view) {
                                                     remove(holder.getAbsoluteAdapterPosition());
                                                 }
                                             }
        );
    }

    public void remove(int position){
        mListItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {

        return mListItems.size();
    }

}
