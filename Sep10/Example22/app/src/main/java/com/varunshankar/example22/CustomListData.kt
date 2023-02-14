package com.varunshankar.example22

import android.os.Parcelable
import android.os.Parcel
import android.os.Parcelable.Creator
import java.util.ArrayList

class CustomListData : Parcelable {
    private var mItemList: MutableList<String>? = null

    //Say how and what to write to parcel
    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeStringList(mItemList)
        //out.writeInt(mItemNo);
    }

    //Say how to read in from parcel
    private constructor(`in`: Parcel) {
        `in`.readStringList(mItemList!!)
    }

    constructor(numItems: Int) {
        //Populate the list with data
        mItemList = ArrayList()
        for (i in 1..numItems) {
            (mItemList as ArrayList<String>).add("Item $i")
        }
    }

    //Don't worry about this for now.
    override fun describeContents(): Int {
        return 0
    }

    //Implement a getter and setter
    val itemList: MutableList<String>?
        get() = mItemList

    fun setItemList(itemList: MutableList<String>?) {
        mItemList = itemList
    }

    companion object CREATOR : Creator<CustomListData> {
        override fun createFromParcel(parcel: Parcel): CustomListData {
            return CustomListData(parcel)
        }

        override fun newArray(size: Int): Array<CustomListData?> {
            return arrayOfNulls(size)
        }
    }

}