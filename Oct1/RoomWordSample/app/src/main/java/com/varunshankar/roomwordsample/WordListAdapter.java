package com.varunshankar.roomwordsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    class WordViewHolder extends RecyclerView.ViewHolder{
        private final TextView wordItemView;

        private WordViewHolder(View view){
            super(view);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords = Collections.emptyList(); //Cached copy of words

    WordListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mWords!=null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }
        else{
            holder.wordItemView.setText("No word");
        }
    }
    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mWords!=null){
            return mWords.size();
        }
        else {
            return 0;
        }
    }
}
