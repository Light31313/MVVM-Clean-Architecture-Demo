package com.giangnh44.pagingdemo.domain.repository

import androidx.paging.PagingData
import com.giangnh44.pagingdemo.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImages(
        query: String
    ): List<Image>

    suspend fun searchImagePaging(
        query: String,
    ): Flow<PagingData<Image>>
}