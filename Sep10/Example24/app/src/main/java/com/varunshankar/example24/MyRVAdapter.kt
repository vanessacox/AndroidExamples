package com.varunshankar.example24

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import java.lang.ClassCastException

class MyRVAdapter(private val mListItems: MutableList<String>) :
    RecyclerView.Adapter<MyRVAdapter.ViewHolder>() {
    private var mContext: Context? = null
    private var mDataPasser: DataPasser? = null

    class ViewHolder(var itemLayout: View) : RecyclerView.ViewHolder(
        itemLayout
    ) {
        var itemTvData: TextView = itemLayout.findViewById<View>(R.id.tv_data) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        mDataPasser = try {
            mContext as DataPasser?
        } catch (e: ClassCastException) {
            throw ClassCastException(mContext.toString() + " must implement DataPasser")
        }
        val layoutInflater = LayoutInflater.from(mContext)
        val myView = layoutInflater.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(myView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTvData.text = mListItems[holder.absoluteAdapterPosition]
        holder.itemLayout.setOnClickListener { mDataPasser!!.passData(holder.absoluteAdapterPosition) }
    }

    fun remove(position: Int) {
        mListItems.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return mListItems.size
    }

    interface DataPasser {
        fun passData(position: Int)
    }
}