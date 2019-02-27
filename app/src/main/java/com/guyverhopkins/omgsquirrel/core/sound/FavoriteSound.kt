package com.guyverhopkins.omgsquirrel.core.sound

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * created by ghopkins 2/27/2019.
 */
@Entity
class FavoriteSound(
    @PrimaryKey(autoGenerate = false)
    private val resourceId: Int,
    private val isFavorite: Boolean = true
)

