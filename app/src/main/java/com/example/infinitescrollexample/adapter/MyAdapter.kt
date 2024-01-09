package com.example.infinitescrollexample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.infinitescrollexample.R
import com.example.infinitescrollexample.model.PhotosResponceModelItem

class MyAdapter(): PagingDataAdapter<PhotosResponceModelItem, MyAdapter.MyInnerViewHolder>(diffCallback) {
    companion object{
        val diffCallback = object : DiffUtil.ItemCallback <PhotosResponceModelItem>(){
            override fun areItemsTheSame(
                oldItem: PhotosResponceModelItem,
                newItem: PhotosResponceModelItem
            ): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PhotosResponceModelItem,
                newItem: PhotosResponceModelItem
            ): Boolean {
                return oldItem==newItem
            }

        }
    }

    inner class MyInnerViewHolder(view:View):ViewHolder(view){
        private val textView: TextView = itemView.findViewById(R.id.satta)

        fun bind(item: PhotosResponceModelItem) {
            textView.text = item.alt_description // Update with your actual data
        }
    }

    override fun onBindViewHolder(holder: MyInnerViewHolder, position: Int) {
       var model = getItem(position)
        if (model != null) {
            holder.bind(model)
        }else{
            Log.e("satta123null", "onBindViewHolder: null data", )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyInnerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_item, parent, false)
        return MyInnerViewHolder(view)
    }
}