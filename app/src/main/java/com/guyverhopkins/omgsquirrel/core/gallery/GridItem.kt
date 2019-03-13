package com.guyverhopkins.omgsquirrel.core.gallery

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
class GridItem : Parcelable {
    var image: String? = null
    var title: String? = null
}