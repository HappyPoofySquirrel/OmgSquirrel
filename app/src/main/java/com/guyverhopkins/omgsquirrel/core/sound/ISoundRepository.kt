package com.guyverhopkins.omgsquirrel.core.sound

import androidx.paging.DataSource

interface ISoundRepository {
    fun getAllSounds(): DataSource.Factory<Int, Sound>
}
