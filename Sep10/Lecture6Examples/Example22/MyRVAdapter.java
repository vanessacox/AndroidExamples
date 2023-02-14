package com.varunshankar.example22;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.ViewHolder> {
    private List<String> mListItems;
    private Context mContext;

    public MyRVAdapter(List<String> inputList) {

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int in_position) {

        int position = holder.getAbsoluteAdapterPosition();
        holder.itemTvData.setText(mListItems.get(position));
        holder.itemLayout.setOnClickListener(new View.OnClickListener(){
                                                 @Override
                                                 public void onClick(View view) {
                                                     remove(position);
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
