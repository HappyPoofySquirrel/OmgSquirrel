package com.guyverhopkins.omgsquirrel.core.gallery

import com.guyverhopkins.omgsquirrel.core.networking.network.NetworkError

interface IFlickrImagesGetter {

    interface Listener {
        fun onGetPictures(flikrResponse: FlikrResponse)
        fun onGetPictureError(networkError: NetworkError)
    }

    fun cancel()

    fun getImages(listener: Listener)
}
