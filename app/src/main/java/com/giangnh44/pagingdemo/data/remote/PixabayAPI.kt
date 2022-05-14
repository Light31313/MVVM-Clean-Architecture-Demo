package com.giangnh44.pagingdemo.data.remote

import com.giangnh44.pagingdemo.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {
    companion object {
        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = "27050845-ca15110a85e62745bd92d7d52"

        private const val DEFAULT_ITEM_PER_PAGE = 20
        private const val DEFAULT_PAGE_NUMBER = 1
    }

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = DEFAULT_PAGE_NUMBER,
        @Query("per_page") perPage: Int = DEFAULT_ITEM_PER_PAGE,
        @Query("key") apiKey: String = API_KEY
    ): Response<ImageResponse>

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = API_KEY
    ): Response<ImageResponse>
}