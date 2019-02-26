package com.guyverhopkins.omgsquirrel.ui.sounds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider
import com.guyverhopkins.omgsquirrel.core.ISoundPlayer
import com.guyverhopkins.omgsquirrel.default

class SoundsViewModel(private val soundPlayer: ISoundPlayer) : ViewModel() {

    private var soundOnFlag = MutableLiveData<Boolean>().default(true)
    var flag: LiveData<Boolean> = soundOnFlag

    private var loopOnFlag = MutableLiveData<Boolean>().default(false)
    var loop: LiveData<Boolean> = loopOnFlag

    fun onBarkPressed() {
        soundPlayer.bark()
    }

    fun soundTogglePressed() {
        soundOnFlag.value?.let {
            soundOnFlag.value = !it
        }
        //todo implement mute or should that just be stop all?
    }

    fun OnLoopTogglePressed() {
        loopOnFlag.value?.let {
            loopOnFlag.value = !it
            soundPlayer.setLoop(!it)
        }
    }

    fun stopAllSounds() {
        soundPlayer.stopAllSounds()
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