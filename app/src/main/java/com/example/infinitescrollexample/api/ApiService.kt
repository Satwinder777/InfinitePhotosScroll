package com.example.infinitescrollexample.api

import com.example.infinitescrollexample.model.PhotosResponceModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization :Client-ID T3B6vHOS4CmnADT5E2vfT7h2z8_PtNm_lek_O9enSPI")
//    @Headers("user-key: 9900a9720d31dfd5fdb4352700c")

    @GET("photos") // Replace with your endpoint
    suspend fun getUsers(@Query("page" ) age:Int): Response<PhotosResponceModel> // Replace User with your data model
}