package com.example.infinitescrollexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.infinitescrollexample.R

class MyLoaderAdapter:LoadStateAdapter<MyLoaderAdapter.MyLoaderViewHolder>() {


    inner class MyLoaderViewHolder(view: View):RecyclerView.ViewHolder(view){

        var loader = view.findViewById<ProgressBar>(R.id.myProg)

        fun bind(state1:LoadState){
            loader.isVisible = state1 is LoadState.Loading
//            loader.isVisible = state1 is LoadState.Error
//            loader.isVisible = state1 is LoadState.Error

//            retryButton.setOnClickListener { retry() }
        }

    }

    override fun onBindViewHolder(holder: MyLoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MyLoaderViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.loader_item,parent,false)
        return MyLoaderViewHolder(view)
    }
}