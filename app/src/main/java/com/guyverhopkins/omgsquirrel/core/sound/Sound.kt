package com.guyverhopkins.omgsquirrel.core.sound

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sound(val pathName: String) {
    @PrimaryKey
    var id: Int = 0
    var resourceId: Int = 0
    var fileName: String = ""
    var isFavorite = false

    init {
        val splitPath = pathName.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        this.fileName = splitPath[splitPath.size - 1]
    }
}