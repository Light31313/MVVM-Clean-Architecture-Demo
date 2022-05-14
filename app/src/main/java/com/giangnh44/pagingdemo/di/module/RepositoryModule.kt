package com.giangnh44.pagingdemo.di.module

import com.giangnh44.pagingdemo.data.repository.ImageRepositoryImpl
import com.giangnh44.pagingdemo.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository
}