package com.example.infinitescrollexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.infinitescrollexample.api.RetrofitClient
import com.example.infinitescrollexample.model.PhotosResponceModel
import com.example.infinitescrollexample.paging.MyPagingSource
import com.example.infinitescrollexample.repository.MyRepository
import com.example.infinitescrollexample.repository.MyRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: MyRepositoryImpl): ViewModel() {

//    var repo = MyRepository(RetrofitClient.apiService)

     var livePhotoslist :LiveData<PhotosResponceModel> = repo.photosList

    suspend fun getPhotos(page:Int){
        repo.getPhotos(page)
//        livePhotoslist.postValue(repo.photosList.value)

    }

    val data = Pager(PagingConfig(pageSize = 1)) {
        MyPagingSource(repo)
    }.flow
}