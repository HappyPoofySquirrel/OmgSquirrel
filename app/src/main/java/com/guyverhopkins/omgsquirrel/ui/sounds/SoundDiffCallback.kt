package com.guyverhopkins.omgsquirrel.ui.sounds

import androidx.recyclerview.widget.DiffUtil
import com.guyverhopkins.omgsquirrel.core.sound.Sound

class SoundDiffCallback : DiffUtil.ItemCallback<Sound>() {
    override fun areContentsTheSame(oldItem: Sound, newItem: Sound): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Sound, newItem: Sound): Boolean {
        return oldItem.id == newItem.id
    }
}
