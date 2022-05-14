package com.giangnh44.pagingdemo.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.giangnh44.pagingdemo.data.remote.PixabayAPI
import com.giangnh44.pagingdemo.data.remote.responses.ImageResult
import retrofit2.HttpException
import java.io.IOException

class PixabayPagingSource(
    private val api: PixabayAPI,
    private val query: String
) : PagingSource<Int, ImageResult>() {

    companion object {
        private const val START_PAGE = 1
        private const val TAG = "PixabayPagingSource"
    }

    override fun getRefreshKey(state: PagingState<Int, ImageResult>): Int? {
        Log.d(TAG, "getRefreshKey: ${state.anchorPosition}")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageResult> {
        return try {
            val page = params.key ?: START_PAGE
            val response = api.searchForImage(query, page)
            val images = response.body()?.hits
            Log.d(TAG, "loadItem: ${images?.size}, page: $page")
            LoadResult.Page(
                data = images ?: listOf(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true
}