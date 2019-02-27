package com.guyverhopkins.omgsquirrel.ui.sounds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guyverhopkins.omgsquirrel.core.common.default
import com.guyverhopkins.omgsquirrel.core.sound.ISoundPlayer

class SoundsViewModel(private val soundPlayer: ISoundPlayer) : ViewModel() {

    private var loopOnFlag = MutableLiveData<Boolean>().default(false)
    var loop: LiveData<Boolean> = loopOnFlag

    fun onBarkPressed() {
        soundPlayer.bark()
    }

    fun onLoopTogglePressed() {
        loopOnFlag.value?.let {
            loopOnFlag.value = !it
            soundPlayer.setLoop(!it)
        }
    }
}

class SoundsViewModelFactory(private val soundPlayer: ISoundPlayer) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SoundsViewModel::class.java)) {
            SoundsViewModel(soundPlayer) as T
        } else {
            throw IllegalArgumentException("Edit ViewModel Not Found")
        }
    }
}