package com.guyverhopkins.omgsquirrel.core.sound

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guyverhopkins.omgsquirrel.core.common.BaseDao

/**
 * created by ghopkins 2/27/2019.
 */
@Dao
abstract class SoundDao : BaseDao<Sound> {

    @Query("SELECT * FROM Sound")
    abstract fun getAll(): DataSource.Factory<Int, Sound>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun instertAll(sounds: Array<Sound>)
}