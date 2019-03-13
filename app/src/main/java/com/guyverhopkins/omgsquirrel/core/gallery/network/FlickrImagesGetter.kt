package com.guyverhopkins.omgsquirrel.core.gallery.network

import android.content.Context
import com.guyverhopkins.omgsquirrel.BuildConfig
import com.guyverhopkins.omgsquirrel.core.networking.network.BaseNetworkManager
import com.guyverhopkins.omgsquirrel.core.networking.network.ClientProvider
import com.guyverhopkins.omgsquirrel.core.networking.network.NetworkError
import com.guyverhopkins.omgsquirrel.core.networking.network.NetworkManager
import com.guyverhopkins.omgsquirrel.core.networking.network.interceptor.CurlLoggingInterceptor
import com.guyverhopkins.omgsquirrel.core.networking.network.request.RequestBuilder
import okhttp3.Authenticator
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 * created by ghopkins 3/4/2019.
 */
private class FlickrImagesGetter(
    private val networkManager: NetworkManager,
    private val requestBuilder: RequestBuilder
) : IFlickrImagesGetter, NetworkManager.NetworkCallback {

    lateinit var listener: IFlickrImagesGetter.Listener

    private var call: Call<FlikrResponse>? = null

    override fun getImages(listener: IFlickrImagesGetter.Listener) {
        this.listener = listener
        val service = requestBuilder.build(Service::class.java)
        call = service.get()
        networkManager.enqueueCall(call as Call<FlikrResponse>, this)
    }

    interface Service {
        @GET("?method=flickr.photosets.getPhotos&oauth_consumer_key=54f1d0146bcec3b405164e253e8ff710&photoset_id=72157661135553275&user_id=137813339%40N03&format=json&nojsoncallback=1&oauth_token=72157663053868089-63166f4e59a184ca&api_sig=d50c3cc7a51f089c28c3efe145e22b6f4aa95f0c")
        fun get(): Call<FlikrResponse>
    }

    override fun cancel() {
        call?.cancel()
    }

    override fun onFailure(error: NetworkError) {
        listener.onGetPictureError(error)
    }

    override fun onResponse(response: Response<*>) {
        val flikrResponse: FlikrResponse = response.body() as FlikrResponse
        listener.onGetPictures(flikrResponse)
    }
}

class FlickrImagesGetterFactory {
    companion object {
        @JvmStatic
        fun build(context: Context): IFlickrImagesGetter {
            return FlickrImagesGetter(
                BaseNetworkManager(),
                createRequestBuilder(
                    context
                )
            )
        }

        fun createRequestBuilder(
            context: Context,
            authenticator: Authenticator? = null,
            readTimeout: Long = 100
        ): RequestBuilder {
            val url =
                "https://api.flickr.com/services/rest/"
            val requestBuilder =
                RequestBuilder(ClientProvider.getInstance().getHttpClient(context, readTimeout)).baseurl(url)

            authenticator?.let {
                requestBuilder.httpClientBuilder.authenticator(it)
            }

//            requestBuilder.httpClientBuilder
//                .addNetworkInterceptor(AuthorizationInterceptor(authHeader))

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                requestBuilder.httpClientBuilder
                    .addInterceptor(loggingInterceptor)

                requestBuilder.httpClientBuilder
                    .addNetworkInterceptor(CurlLoggingInterceptor())
            }

            return requestBuilder
        }
    }
}