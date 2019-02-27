package com.guyverhopkins.omgsquirrel.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.guyverhopkins.omgsquirrel.core.sound.Sound
import com.guyverhopkins.omgsquirrel.core.sound.SoundDao

/**
 * created by ghopkins 2/27/2019.
 */
@Database(entities = [Sound::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun soundDao(): SoundDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "SoundDB")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries() //
                        .build()
                }
            }
            return INSTANCE
        }
    }
}