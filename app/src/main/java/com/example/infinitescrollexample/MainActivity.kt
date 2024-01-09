package com.example.infinitescrollexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.infinitescrollexample.adapter.MyAdapter
import com.example.infinitescrollexample.viewmodel.MyViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val viewmodel : MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewmodel.getPhotos(2)
        }
        viewmodel.livePhotoslist.observe(this, Observer {

            if(it!=null){
                it.forEach{
                    Log.e("satta112345", "onCreate: ${it.id}", )
                }
            }

        })
        var rc = findViewById<RecyclerView>(R.id.MyRC)
        var adapter = MyAdapter ()
        rc.adapter = adapter
        viewmodel.data.asLiveData().observe(this) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }
    }
}