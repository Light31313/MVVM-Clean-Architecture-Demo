package com.giangnh44.pagingdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.giangnh44.pagingdemo.databinding.ItemImageBinding
import com.giangnh44.pagingdemo.domain.model.Image
import javax.inject.Inject

class PagingImageAdapter @Inject constructor(private val glide: RequestManager) :
    PagingDataAdapter<Image, PagingImageAdapter.ImageViewHolder>(diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var onItemClickListener: ((Image) -> Unit)? = null

    inner class ImageViewHolder(
        private val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) = with(binding) {
            glide.load(image.previewURL).into(this.image)
            this@ImageViewHolder.itemView.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(image)
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Image) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

}