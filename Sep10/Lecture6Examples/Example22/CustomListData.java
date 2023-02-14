package com.varunshankar.example22;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CustomListData implements Parcelable {

    private List<String> mItemList;

    //Say how and what to write to parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeStringList(mItemList);
    }

    //Say how to read in from parcel
    private CustomListData(Parcel in){
        in.readStringList(mItemList);
    }

    public CustomListData(int numItems){
        //Populate the list with data
        mItemList = new ArrayList<>();
        for (int i=1;i<=numItems;i++){
            mItemList.add("Item " + i);
        }
    }

    //Don't worry about this for now.
    @Override
    public int describeContents() {
        return 0;
    }

    //Implement the creator method
    public static final Parcelable.Creator<CustomListData> CREATOR = new Parcelable.Creator<CustomListData>(){

        //Call the private constructor
        @Override
        public CustomListData createFromParcel(Parcel in) {
            return new CustomListData(in);
        }

        @Override
        public CustomListData[] newArray(int size) {
            return new CustomListData[size];
        }
    };

    //Implement a getter and setter
    public List<String> getItemList(){
        return mItemList;
    }

    public void setItemList(List<String> itemList){
        mItemList = itemList;
    }
}
