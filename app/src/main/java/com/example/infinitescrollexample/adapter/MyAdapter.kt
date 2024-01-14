package com.example.infinitescrollexample.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
        private val img: ImageView = itemView.findViewById(R.id.myImg)

        fun bind(item: PhotosResponceModelItem) {
            textView.text = item.alt_description // Update with your actual data
//            img.setImageURI(Uri.parse(item.urls.regular))
            Glide.with(itemView.context)
                .load(item.urls.regular)
                .placeholder(R.drawable.baseline_image_24) // Placeholder image while loading
                .error(R.drawable.baseline_error_outline_24) // Image to display on error
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img)
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