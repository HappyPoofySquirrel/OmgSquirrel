package com.guyverhopkins.omgsquirrel.core.sound

import androidx.paging.DataSource

/**
 * created by ghopkins 2/27/2019.
 */
class SoundRepository(private val dao: SoundDao) : ISoundRepository {
    override fun updateSound(sound: Sound) {
        dao.update(sound)
    }

    override fun getAllSounds(): DataSource.Factory<Int, Sound> {
        return dao.getAll()
    }
}