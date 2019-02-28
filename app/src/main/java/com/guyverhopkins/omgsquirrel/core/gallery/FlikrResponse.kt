package com.guyverhopkins.omgsquirrel.core.gallery

import com.google.gson.annotations.SerializedName

data class FlikrResponse(
    @SerializedName("photoset")
    val photoset: Photoset = Photoset(),
    @SerializedName("stat")
    val stat: String = ""
)

data class Photoset(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("owner")
    val owner: String = "",
    @SerializedName("ownername")
    val ownername: String = "",
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("pages")
    val pages: Int = 0,
    @SerializedName("per_page")
    val perPage: Int = 0,
    @SerializedName("perpage")
    val perpage: Int = 0,
    @SerializedName("photo")
    val photo: List<Photo> = listOf(),
    @SerializedName("primary")
    val primary: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("total")
    val total: Int = 0
)

data class Photo(
    @SerializedName("farm")
    val farm: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("isfamily")
    val isfamily: Int = 0,
    @SerializedName("isfriend")
    val isfriend: Int = 0,
    @SerializedName("isprimary")
    val isprimary: String = "",
    @SerializedName("ispublic")
    val ispublic: Int = 0,
    @SerializedName("secret")
    val secret: String = "",
    @SerializedName("server")
    val server: String = "",
    @SerializedName("title")
    val title: String = ""
)