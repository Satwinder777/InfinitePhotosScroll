package com.example.infinitescrollexample.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emp_data")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "role") var role: String,
    @ColumnInfo(name = "mobile") var mobile: String
)
