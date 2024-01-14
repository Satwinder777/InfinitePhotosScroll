package com.example.infinitescrollexample.di

import com.example.infinitescrollexample.api.ApiService
import com.example.infinitescrollexample.api.MyInterceptor
import com.example.infinitescrollexample.api.RetrofitClient
import com.example.infinitescrollexample.repository.MyRepository
import com.example.infinitescrollexample.repository.MyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{

        var client = OkHttpClient.Builder().addInterceptor(MyInterceptor()).build()
        return Retrofit.Builder()
            .baseUrl(RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService):MyRepository{
        return MyRepositoryImpl(apiService)
    }


}