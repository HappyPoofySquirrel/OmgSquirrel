package com.guyverhopkins.omgsquirrel.core.gallery.network

import android.content.Context
import com.guyverhopkins.omgsquirrel.BuildConfig
import com.guyverhopkins.omgsquirrel.core.networking.network.ClientProvider
import com.guyverhopkins.omgsquirrel.core.networking.network.INetworkManager
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
    private val networkManager: INetworkManager,
    private val requestBuilder: RequestBuilder
) : IFlickrImagesGetter, INetworkManager.NetworkCallback {

    private val perpage = 30 //todo make larger if tablet? or smaller is amller screen?

    private lateinit var listener: IFlickrImagesGetter.Listener

    private var call: Call<FlikrResponse>? = null

    override fun getImages(listener: IFlickrImagesGetter.Listener) {
        this.listener = listener
        val service = requestBuilder.build(Service::class.java)
        call = service.get()
        networkManager.enqueueCall(call as Call<FlikrResponse>, this)
    }

    //https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/Search/ImageSearchAPI?autoCorrect=true&pageNumber=1&pageSize=10&q=6&safeSearch=false
    interface Service {
        @GET("api/search/images/?q=squirrel")
        fun get(): Call<FlikrResponse>
    }

    override fun cancel() {
        call?.cancel()
    }

    override fun onResponse(response: Response<*>) {
        val flikrResponse: FlikrResponse = response.body() as FlikrResponse
        listener.onGetPictures(flikrResponse)
    }

    override fun onFailure(error: NetworkError) {
        listener.onGetPictureError(error)
    }
}

class FlickrImagesGetterFactory {
    companion object {
        @JvmStatic
        fun build(context: Context): IFlickrImagesGetter {
            return FlickrImagesGetter(
                NetworkManager(),
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
                BuildConfig.FLIKR_URL
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