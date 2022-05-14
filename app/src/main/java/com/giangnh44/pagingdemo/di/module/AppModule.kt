package com.giangnh44.pagingdemo.di.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.giangnh44.pagingdemo.R
import com.giangnh44.pagingdemo.data.remote.PixabayAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .baseUrl(PixabayAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixabayAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ): RequestManager = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
    )

}