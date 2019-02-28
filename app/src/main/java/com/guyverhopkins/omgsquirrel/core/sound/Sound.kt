package com.guyverhopkins.omgsquirrel.core.sound

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guyverhopkins.omgsquirrel.R

@Entity
data class Sound(
    val resourceId: Int,
    val displayName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var fileName: String = ""
    var isFavorite = false

    companion object {
        fun populateDataBase(): Array<Sound> {
            return arrayOf(
                Sound(R.raw.barking, "Angry Bark"),
                Sound(R.raw.crywhinee, "Sad Day"),
                Sound(R.raw.funkypursqueek, "Excited chatter"),
                Sound(R.raw.purr, "Happy chatter"),
                Sound(R.raw.squakshort, "Angry Warning!!!")

            )
        }
    }
}

