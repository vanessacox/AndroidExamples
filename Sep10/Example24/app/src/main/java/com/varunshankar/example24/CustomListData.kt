package com.varunshankar.example24

import android.os.Parcelable
import android.os.Parcel
import android.os.Parcelable.Creator
import java.util.ArrayList

class CustomListData : Parcelable {
    private var mItemList: MutableList<String>? = null
    private var mItemDetails: MutableList<String>? = null

    //Say how to read in from parcel
    private constructor(`in`: Parcel) {
        `in`.readStringList(mItemList!!)
        `in`.readStringList(mItemDetails!!)
    }

    constructor(numItems: Int) {
        //Populate the item list with data
        //and populate the details list with details at the same time
        mItemList = ArrayList()
        mItemDetails = ArrayList()
        for (i in 1..numItems) {
            (mItemList as ArrayList<String>).add("Item $i")
            (mItemDetails as ArrayList<String>).add("Item " + i + " is awesome, and unique because of this random number: " + Math.random())
        }
    }

    //Say how and what to write to parcel
    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeStringList(mItemList)
        out.writeStringList(mItemDetails)
    }

    //Don't worry about this for now.
    override fun describeContents(): Int {
        return 0
    }

    //Implement a getter and setter for getting whole list
    val itemList: MutableList<String>?
        get() = mItemList

    fun setItemList(itemList: MutableList<String>?) {
        mItemList = itemList
    }

    //Implement getter for item details at a position
    fun getItemDetail(position: Int): String {
        return mItemDetails!![position]
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