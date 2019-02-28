package com.guyverhopkins.omgsquirrel.core.sound

import androidx.paging.PagedList

interface ISoundPlayer {
    fun setLoop(loop: Boolean)
    fun release()
    fun setSounds(sounds: PagedList<Sound>?)
    fun playSound(position: Int)
}
