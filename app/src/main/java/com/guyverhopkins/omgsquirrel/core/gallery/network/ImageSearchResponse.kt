package com.guyverhopkins.omgsquirrel.core.gallery.network

import com.google.gson.annotations.SerializedName


/**
 * created by ghopkins 3/28/2019.
 */
data class ImageSearchResponse(
    @SerializedName("_type")
    val type: String,
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("value")
    val value: List<Value>
)

data class Value(
    @SerializedName("base64Encoding")
    val base64Encoding: Any,
    @SerializedName("height")
    val height: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("thumbnailHeight")
    val thumbnailHeight: Int,
    @SerializedName("thumbnailWidth")
    val thumbnailWidth: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)