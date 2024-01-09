package com.example.infinitescrollexample.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.infinitescrollexample.api.ApiService
import com.example.infinitescrollexample.api.RetrofitClient
import com.example.infinitescrollexample.model.PhotosResponceModel
import com.example.infinitescrollexample.model.PhotosResponceModelItem
import com.example.infinitescrollexample.repository.MyRepository

class MyPagingSource(private val repo: MyRepository): PagingSource<Int, PhotosResponceModelItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotosResponceModelItem> {
        val page = params.key ?: 1
        val responseq = repo.getPhotos(page)
        // Handle network errors and API responses appropriately
        Log.e("satta213", "load: $page" , )
        return LoadResult.Page(
            data = responseq?.toList()!!,
            prevKey = if (page == 1) null else page - 1 ,
            nextKey = if (page == 20) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PhotosResponceModelItem>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}