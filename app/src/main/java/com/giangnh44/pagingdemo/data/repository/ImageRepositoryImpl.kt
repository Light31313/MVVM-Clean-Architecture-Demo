package com.giangnh44.pagingdemo.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.giangnh44.pagingdemo.data.mapper.toImage
import com.giangnh44.pagingdemo.data.paging.PixabayPagingSource
import com.giangnh44.pagingdemo.data.remote.PixabayAPI
import com.giangnh44.pagingdemo.domain.model.Image
import com.giangnh44.pagingdemo.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: PixabayAPI
) : ImageRepository {
    override suspend fun getImages(query: String): List<Image> {
        return try {
            val response = api.searchForImage(query)
            if (response.isSuccessful) {
                response.body()?.let { imageResponse ->
                    Log.d("MyTag", "getImages: ${response.body()?.hits}")
                    return@let imageResponse.hits.map {
                        it.toImage()
                    }
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun searchImagePaging(query: String): Flow<PagingData<Image>> {
        return Pager(
            PagingConfig(pageSize = 40)
        ) {
            PixabayPagingSource(api, query)
        }.flow.map { pagingData ->
            pagingData.map { it.toImage() }
        }
    }


}