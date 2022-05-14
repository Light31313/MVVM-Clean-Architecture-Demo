package com.giangnh44.pagingdemo.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.giangnh44.pagingdemo.R

@BindingAdapter("imageUrl")
fun ImageView.loadImageFromUrl(url: String?) {
    Glide.with(this).load(url)
        .placeholder(R.drawable.ic_baseline_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}