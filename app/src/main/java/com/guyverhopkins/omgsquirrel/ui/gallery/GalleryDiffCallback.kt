package com.guyverhopkins.omgsquirrel.ui.gallery

import androidx.recyclerview.widget.DiffUtil
import com.guyverhopkins.omgsquirrel.core.gallery.GridItem

class GalleryDiffCallback : DiffUtil.ItemCallback<GridItem>() {
    override fun areContentsTheSame(oldItem: GridItem, newItem: GridItem): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: GridItem, newItem: GridItem): Boolean {
        return oldItem.image == newItem.image
    }
}

