package com.guyverhopkins.omgsquirrel.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guyverhopkins.omgsquirrel.core.gallery.network.FlikrResponse
import com.guyverhopkins.omgsquirrel.core.gallery.network.IFlickrImagesGetter
import com.guyverhopkins.omgsquirrel.core.networking.network.NetworkError

class GalleryViewModel(private val imagesGetter: IFlickrImagesGetter) : ViewModel(), IFlickrImagesGetter.Listener {
    override fun onGetPictures(flikrResponse: FlikrResponse) {
        val x = 9
    }

    override fun onGetPictureError(networkError: NetworkError) {
        val x = 9

    }


    fun getImages() {
        imagesGetter.getImages(this)
    }
}

class GalleryViewModelFactory(private val flickrImagesGetter: IFlickrImagesGetter) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            GalleryViewModel(flickrImagesGetter) as T
        } else {
            throw IllegalArgumentException("Edit ViewModel Not Found")
        }
    }
}