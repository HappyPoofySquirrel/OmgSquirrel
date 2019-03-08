package com.guyverhopkins.omgsquirrel.ui.gallery.galleryitem

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.guyverhopkins.omgsquirrel.core.gallery.GridItem

/**
 * created by ghopkins 3/4/2019.
 */

class GridItemPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private var images: ArrayList<GridItem>? = null

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        val image = images?.get(position)
        bundle.putParcelable(GridImageFragment.IMAGE_BUNDLE_KEY, image)

        return Fragment.instantiate(context, GridImageFragment::class.java.name, bundle)
    }

    override fun getCount(): Int {
        return images?.size ?: 0
    }

    fun setData(images: ArrayList<GridItem>) {
        this.images = images
    }
}
