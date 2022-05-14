package com.giangnh44.pagingdemo.data.mapper

import com.giangnh44.pagingdemo.data.remote.responses.ImageResult
import com.giangnh44.pagingdemo.domain.model.Image

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


