package com.guyverhopkins.omgsquirrel.core

import android.content.ContentValues.TAG
import android.content.Context
import android.media.SoundPool
import android.content.res.AssetFileDescriptor
import android.util.Log
import java.io.IOException
import android.content.res.AssetManager
import android.media.AudioManager
import com.guyverhopkins.omgsquirrel.R


/**
 * created by ghopkins 2/25/2019.
 * i dont like that i need context here figure out a way to omit it
 */
class SoundPlayer(private val context: Context?) : ISoundPlayer, SoundPool.OnLoadCompleteListener {
    override fun onLoadComplete(p0: SoundPool?, p1: Int, p2: Int) {
        loaded = true
    }

    private var loaded = false
    private val SOUND_FOLDER = "sounds"
    //    private val soundPool: SoundPool by lazy { SoundPool.Builder().setMaxStreams(1).build() }
    private val soundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    private val soundList = mutableListOf<Sound>()
    private val assetManager: AssetManager? = null

    val barkId = soundPool.load(context, R.raw.barking, 1)
    init {
        soundPool.setOnLoadCompleteListener(this)
    }

    override fun bark() {
//        soundPool.load(context, context?.resources?.getIdentifier("barking", "raw/sounds", context?.packageName))
//        val barkId = soundPool.load(context, R.raw.barking, 1)
        loaded?.let {
            soundPool.play(barkId, 1f, 1f, 1, 0, 1f)
        }
    }

    private fun fetchSounds() {
        val soundFiles: Array<String>
        try {
//            soundFiles = assetManager.list(SOUND_FOLDER)
//            Log.d(TAG, "Fetched " + soundFiles.size + " sound files")
        } catch (e: IOException) {
            Log.e(TAG, "Error accessing sound folder", e)
            return
        }

//        for (fileName in soundFiles) {
//            try {
//                val path = SOUND_FOLDER + "/" + fileName
//                val s = Sound(path)
//                load(s)
//                soundList.add(s)
//            } catch (e: IOException) {
//                Log.e(TAG, "Could not load sound: $fileName", e)
//            }
//
//        }
    }

    @Throws(IOException::class)
    private fun load(sound: Sound) {
        val fileDescriptor = assetManager?.openFd(sound.pathName)
        val soundId = soundPool.load(fileDescriptor, 1)
        sound.id = soundId
    }

    fun play(sound: Sound) {
        val soundId = sound.id ?: return
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun release() {
//        Log.d(TAG, "Cleaning resources..")
        soundPool.release()
    }

    fun getSounds(): List<Sound> {
        return this.soundList
    }
}

