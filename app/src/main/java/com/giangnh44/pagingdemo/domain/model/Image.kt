package com.giangnh44.pagingdemo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val comments: Int,
    val id: Int,
    val largeImageURL: String,
    val previewURL: String,
    val likes: Int,
    val tags: String,
    val type: String,
    val user: String,
    val views: Int,
) : Parcelable