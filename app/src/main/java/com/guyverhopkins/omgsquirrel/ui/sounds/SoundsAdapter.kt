package com.guyverhopkins.omgsquirrel.ui.sounds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.guyverhopkins.omgsquirrel.R
import com.guyverhopkins.omgsquirrel.core.sound.Sound

/**
 * created by ghopkins 2/27/2019.
 */
class SoundsAdapter : PagedListAdapter<Sound, SoundsAdapter.SoundViewHolder>(SoundDiffCallback()) {
    private var itemClicklistener: ItemClickListener? = null
    private var favoriteToggleListener: FavoriteToggleListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        return SoundViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sound_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        val sound = getItem(position)
        holder.name.text = sound?.displayName
        holder.favoriteToggle.isEnabled = sound?.isFavorite ?: false
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.itemClicklistener = listener
    }

    fun setFavorToggleListener(favoriteToggleListener: FavoriteToggleListener) {
        this.favoriteToggleListener = favoriteToggleListener
    }

    interface ItemClickListener {
        fun onSoundPressed(position: Int)
    }

    interface FavoriteToggleListener {
        fun onFavoriteToggled(isChecked: Boolean, position: Int)
    }

    inner class SoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView = itemView.findViewById(R.id.imageView)
        var name: TextView = itemView.findViewById(R.id.textView)
        var favoriteToggle: ToggleButton = itemView.findViewById(R.id.toggleButton)

        init {
            itemView.setOnClickListener {
                itemClicklistener?.let {
                    it.onSoundPressed(adapterPosition)
                }
            }

            favoriteToggle.setOnCheckedChangeListener { _, b ->
                favoriteToggleListener?.let {
                    it.onFavoriteToggled(b, adapterPosition)
                }
            }
        }
    }

}
