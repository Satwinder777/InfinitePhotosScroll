package com.example.infinitescrollexample.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.infinitescrollexample.R
import com.example.infinitescrollexample.databinding.RoomitemBinding
import com.example.infinitescrollexample.room.entity.TodoEntity

class RoomAdapter():ListAdapter<TodoEntity, RoomAdapter.roomViewHolder>(RoomDiffUtil()) {



    class RoomDiffUtil:DiffUtil.ItemCallback<TodoEntity>(){
        override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem==newItem
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): roomViewHolder {
//        var view = LayoutInflater.from(parent.context).inflate(R.layout.roomitem,parent,false)
        var view = RoomitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return roomViewHolder(view)
    }

    inner class roomViewHolder(val binding:RoomitemBinding):ViewHolder(binding.root){
        var userId = binding.userid
        var nameUser1 = binding.dvname
        var role1 = binding.dvrole

        fun bind(item:TodoEntity){
            userId.setText(item.id.toString())
            nameUser1.setText(item.name.toString())
            role1.setText(item.role.toString())
        }
    }

    override fun onBindViewHolder(holder: roomViewHolder, position: Int) {
        var data = getItem(position)
        holder.bind(data)
    }
}