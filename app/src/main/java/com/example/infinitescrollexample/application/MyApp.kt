package com.example.infinitescrollexample.application

import android.app.Application
import android.content.Context
import com.example.infinitescrollexample.room.db.AppDatabase

class MyApp:Application() {

    private lateinit var db1 :AppDatabase



//    init {
//        db1 = AppDatabase(this)
//    }
    override fun onCreate() {
        super.onCreate()
        db1 = AppDatabase(this)
    }

//    fun db() = db1

}