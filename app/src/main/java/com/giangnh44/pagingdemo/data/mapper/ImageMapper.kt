package com.giangnh44.pagingdemo.data.mapper

import android.util.Log
import com.giangnh44.pagingdemo.domain.model.Image
import com.giangnh44.pagingdemo.data.remote.responses.ImageResult

fun ImageResult.toImage(): Image {
    return Image(
        id = id,
        previewURL = previewURL,
        user = user,
        type = type,
        comments = comments,
        largeImageURL = largeImageURL,
        likes = likes,
        tags = tags,
        views = views
    )
}


