package com.guyverhopkins.omgsquirrel.core.sound

import android.content.Context
import android.media.SoundPool
import androidx.paging.PagedList


/**
 * created by ghopkins 2/25/2019.
 * i dont like that i need context here figure out a way to omit it
 */
class SoundPlayer(private val context: Context?) : ISoundPlayer, SoundPool.OnLoadCompleteListener {
    private var loaded = false
    private val soundPool = SoundPool.Builder().setMaxStreams(1).build()
    private lateinit var playableSounds: IntArray
    private var loop = 0
    private var currentSound: Int = 0

    override fun setSounds(sounds: PagedList<Sound>?) {
        sounds?.let {
            playableSounds = IntArray(it.size)

            for ((index, value) in sounds.iterator().withIndex()) {
                playableSounds[index] = soundPool.load(context, value.resourceId, 1)
            }
        }
    }

    override fun onLoadComplete(p0: SoundPool?, p1: Int, p2: Int) {
        loaded = true
    }

    override fun setLoop(shouldLoop: Boolean) {
        loop = if (shouldLoop) {
            -1
        } else {
            0
        }

        if (!shouldLoop) {
            soundPool.stop(currentSound)
        }
    }


    override fun playSound(position: Int) {
        currentSound = playableSounds[position]
        soundPool.play(currentSound, 1f, 1f, 1, loop, 1f)
    }

    init {
        soundPool.setOnLoadCompleteListener(this)
    }

    override fun release() {
        soundPool.release()
    }
}

