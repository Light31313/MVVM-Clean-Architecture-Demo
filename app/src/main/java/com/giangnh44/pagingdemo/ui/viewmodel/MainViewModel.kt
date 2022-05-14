package com.giangnh44.pagingdemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.giangnh44.pagingdemo.domain.model.Image
import com.giangnh44.pagingdemo.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {
    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>>
        get() = _images

    private val _pagingImages = MutableLiveData<PagingData<Image>>()
    val pagingImages: LiveData<PagingData<Image>>
        get() = _pagingImages

    fun searchImage(query: String) {
        viewModelScope.launch {
            val fetchImages = async { imageRepository.getImages(query) }
            _images.value = fetchImages.await()
        }
    }

    fun searchImagePaging(query: String) {
        viewModelScope.launch {
            imageRepository.searchImagePaging(query).cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _pagingImages.value = pagingData
                }
        }
    }
}