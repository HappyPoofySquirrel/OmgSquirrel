package com.guyverhopkins.omgsquirrel.ui.sounds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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
        holder.nameTv.text = sound?.displayName
        val isFavorite = sound?.isFavorite ?: false
        setFavoriteIcon(isFavorite, holder)

        holder.containerCl.setOnClickListener {
            val viewHolder = it.tag as SoundViewHolder
            itemClicklistener?.onSoundPressed(viewHolder.adapterPosition)
        }
        holder.containerCl.tag = holder

        holder.ivFavoriteBtn.setOnClickListener {
            var isFavorite = sound?.isFavorite ?: false
            isFavorite = !isFavorite
            setFavoriteIcon(isFavorite, holder)
            val viewHolder = it.tag as SoundViewHolder
            favoriteToggleListener?.onFavoriteToggled(isFavorite, viewHolder.adapterPosition)

            sound?.isFavorite = isFavorite
        }
        holder.ivFavoriteBtn.tag = holder
    }

    private fun setFavoriteIcon(isFavorite: Boolean, holder: SoundViewHolder) {
        val scaleAnimation =
            ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f)
        scaleAnimation.duration = 500
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation.interpolator = bounceInterpolator

        holder.ivFavoriteBtn.startAnimation(scaleAnimation)

        var drawableResource = R.drawable.ic_star_border_outline_24dp
        if (isFavorite) {
            drawableResource = R.drawable.ic_star_filled_24dp
        }

        holder.ivFavoriteBtn.setImageDrawable(
            ContextCompat.getDrawable(
                holder.itemView.context,
                drawableResource
            )
        )
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
        var iconIv: ImageView = itemView.findViewById(R.id.iv_sound_icon)
        var nameTv: TextView = itemView.findViewById(R.id.tv_sound_name)
        var ivFavoriteBtn = itemView.findViewById(R.id.iv_sound_favorite) as ImageView
        var containerCl = itemView.findViewById(R.id.cl_sound_item_container) as ConstraintLayout
    }

}
