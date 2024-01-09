package com.example.infinitescrollexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.infinitescrollexample.adapter.MyAdapter
import com.example.infinitescrollexample.adapter.MyLoaderAdapter
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
//        adapter.

//        val concatAdapter = ConcatAdapter(
//            adapter,
////            ItemLoadStateAdapter(::retry) // Pass a retry function
//            MyLoaderAdapter()
//
//        )
//        adapter.withLoadStateFooter(MyLoaderAdapter())
//        var loader = findViewById<ProgressBar>(R.id.progg)
        rc.adapter = adapter.withLoadStateHeaderAndFooter(MyLoaderAdapter(),MyLoaderAdapter())
//        adapter.addLoadStateListener { loadState ->
//            when(loadState as LoadState){
//                LoadState.Loading->{
//                    Handler().postDelayed({
//                        loader.visibility = View.VISIBLE
//                    },1000)
//                }
////                  LoadState.NotLoading ->{}
////                is LoadState.Error->{}
//                else->{  Handler().postDelayed({
//                    loader.visibility = View.GONE
//                },1000)}
//            }
//            // Handle other states as needed
//        }
        viewmodel.data.asLiveData().observe(this) { pagingData ->

            adapter.submitData(lifecycle, pagingData)

        }
    }
}