package com.guyverhopkins.omgsquirrel.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.guyverhopkins.omgsquirrel.R
import com.guyverhopkins.omgsquirrel.core.gallery.GridItem
import com.squareup.picasso.Picasso

/**
 * created by ghopkins 2/28/2019.
 */
class GalleryAdapter(private val context: Context) :
    PagedListAdapter<GridItem, GalleryAdapter.GalleryViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val image = getItem(position)
        //todo use image.image
        Picasso.with(context).load("https://c1.staticflickr.com/1/680/22549436148_873dff5107_b.jpg")
            .into(holder.image)
    }

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.iv_grid_item)
    }
}