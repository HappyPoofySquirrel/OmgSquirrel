package com.guyverhopkins.omgsquirrel.core.common

import android.app.Application
import com.guyverhopkins.omgsquirrel.core.AppDatabase
import com.guyverhopkins.omgsquirrel.core.sound.ISoundRepository
import com.guyverhopkins.omgsquirrel.core.sound.SoundRepository

/**
 * Created by guyverhopkins on 10/18/16.
 */
class RepositoryProvider private constructor() {

    internal var soundRepository: ISoundRepository? = null

    fun getTaskRepository(application: Application): ISoundRepository {
        if (soundRepository == null) {
            val dao = AppDatabase.getAppDataBase(application)!!.soundDao() //todo handle if its null
            soundRepository = SoundRepository(dao)
        }
        return soundRepository as ISoundRepository
    }

    companion object {
        val instance = RepositoryProvider()
    }
}