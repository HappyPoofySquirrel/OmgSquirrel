package com.guyverhopkins.omgsquirrel.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.guyverhopkins.omgsquirrel.R
import com.guyverhopkins.omgsquirrel.core.gallery.GridItem

/**
 * created by ghopkins 2/28/2019.
 */
class GalleryAdapter(private var images: Array<GridItem>) : BaseAdapter() {

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
        val image = images[position]
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.grid_item_layout, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.iv_grid_item)
        imageView.setImageDrawable(ContextCompat.getDrawable(parent!!.context, R.drawable.ic_star_filled_24dp))
        return view
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return images.size
    }

    fun setImages(images: Array<GridItem>) {
        this.images = images
        notifyDataSetChanged()

    }

}