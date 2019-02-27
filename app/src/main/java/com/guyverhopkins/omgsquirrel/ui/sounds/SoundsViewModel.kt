package com.guyverhopkins.omgsquirrel.ui.sounds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.guyverhopkins.omgsquirrel.core.common.default
import com.guyverhopkins.omgsquirrel.core.sound.ISoundPlayer
import com.guyverhopkins.omgsquirrel.core.sound.ISoundRepository
import com.guyverhopkins.omgsquirrel.core.sound.Sound

class SoundsViewModel(private val repo: ISoundRepository, private val soundPlayer: ISoundPlayer) : ViewModel() {

    private var loopOnFlag = MutableLiveData<Boolean>().default(false)
    var loop: LiveData<Boolean> = loopOnFlag

    var sounds: LiveData<PagedList<Sound>>

    init {
        val factory: DataSource.Factory<Int, Sound> = repo.getAllSounds()
        val pagedListBuilder: LivePagedListBuilder<Int, Sound> = LivePagedListBuilder<Int, Sound>(factory, 10)
        sounds = pagedListBuilder.build()
    }

    fun onBarkPressed() {
        soundPlayer.bark()
    }

    fun onLoopTogglePressed() {
//        val sound = Sound("test")

//        sound.displayName = "mad"
//        sound.isFavorite = true
//        soundsList.value = listOf(sound)

        loopOnFlag.value?.let {
            loopOnFlag.value = !it
            soundPlayer.setLoop(!it)
        }
    }
}

class SoundsViewModelFactory(private val soundsRepo: ISoundRepository, private val soundPlayer: ISoundPlayer) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SoundsViewModel::class.java)) {
            SoundsViewModel(soundsRepo, soundPlayer) as T
        } else {
            throw IllegalArgumentException("Edit ViewModel Not Found")
        }
    }
}