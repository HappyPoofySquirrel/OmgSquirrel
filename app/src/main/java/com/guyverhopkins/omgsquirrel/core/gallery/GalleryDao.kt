package com.guyverhopkins.omgsquirrel.core.gallery

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guyverhopkins.omgsquirrel.core.common.BaseDao
import com.guyverhopkins.omgsquirrel.core.sound.Sound

@Dao
abstract class GalleryDao : BaseDao<GridItem> {

    @Query("SELECT * FROM GridItem")
    abstract fun getAll(): DataSource.Factory<Int, GridItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun instertAll(sounds: Array<Sound>)
}
