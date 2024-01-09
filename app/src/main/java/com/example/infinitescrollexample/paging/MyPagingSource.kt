package com.example.infinitescrollexample.paging

import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.infinitescrollexample.api.ApiService
//import com.example.infinitescrollexample.api.RetrofitClient
//import com.example.infinitescrollexample.model.PhotosResponceModel
//import com.example.infinitescrollexample.model.PhotosResponceModelItem
//import com.example.infinitescrollexample.repository.MyRepository
//
//class MyPagingSource(private val repo: MyRepository): PagingSource<Int, PhotosResponceModel>() {
//
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosResponceModel> {
//        val page = params.key ?: 1
//        val responseq = repo.getPhotos(page)
//        // Handle network errors and API responses appropriately
//        return LoadResult.Page(
//            data = repo,
//            prevKey = if (page == 1) null else page - 1,
//            nextKey = if (response.isLastPage) null else page + 1
//        )
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, PhotosResponceModel>): Int? {
//        TODO("Not yet implemented")
//    }
//}