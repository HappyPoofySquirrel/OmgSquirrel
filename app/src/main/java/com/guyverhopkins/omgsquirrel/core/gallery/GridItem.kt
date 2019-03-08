package com.guyverhopkins.omgsquirrel.core.gallery

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GridItem : Parcelable {
    var image: String? = null
    var title: String? = null
}