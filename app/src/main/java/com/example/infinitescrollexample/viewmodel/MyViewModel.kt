package com.example.infinitescrollexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infinitescrollexample.api.RetrofitClient
import com.example.infinitescrollexample.model.PhotosResponceModel
import com.example.infinitescrollexample.repository.MyRepository
import retrofit2.Retrofit

class MyViewModel(): ViewModel() {

    var repo = MyRepository(RetrofitClient.apiService)

     var livePhotoslist :LiveData<PhotosResponceModel> = repo.photosList

    suspend fun getPhotos(page:Int){
        repo.getPhotos(page)
//        livePhotoslist.postValue(repo.photosList.value)

    }
}