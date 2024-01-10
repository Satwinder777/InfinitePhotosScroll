package com.example.infinitescrollexample.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.infinitescrollexample.api.ApiService
import com.example.infinitescrollexample.model.PhotosResponceModel
import com.example.infinitescrollexample.model.PhotosResponceModelItem

class MyRepository(private val apiService: ApiService) {

    private var _photosList = MutableLiveData<PhotosResponceModel>()
    var photosList : LiveData<PhotosResponceModel> = _photosList

    suspend fun getPhotos(page:Int): PhotosResponceModel?{
//        var list = arrayListOf<PhotosResponceModelItem>()
        var token = "Client-ID T3B6vHOS4CmnADT5E2vfT7h2z8_PtNm_lek_O9enSPI"
        var responce = apiService.getUsers(token,page,20)
        if (responce.isSuccessful){
            _photosList.postValue(responce.body())
            Log.e("SherGillProduction", "getPhotos: ${responce.code()}", )
//            Log.e("SherGillProduction", "getPhotos: ${responce.body()}", )
//            responce.body()?.forEach {
//                list.add(it)
//            }
        }else{
            Log.e("SherGillProduction", "getPhotos: ${responce.message()}", )
        }
        return responce.body()
    }


}