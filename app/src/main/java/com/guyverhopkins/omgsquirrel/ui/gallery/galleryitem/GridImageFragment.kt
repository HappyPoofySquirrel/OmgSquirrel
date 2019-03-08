package com.guyverhopkins.omgsquirrel.ui.gallery.galleryitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guyverhopkins.omgsquirrel.R
import com.guyverhopkins.omgsquirrel.core.gallery.GridItem
import com.squareup.picasso.Picasso

class GridImageFragment : Fragment() {

    private lateinit var url: String

    private var ivImage: TouchImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_grid_image, container, false)

        ivImage = view.findViewById(R.id.tiv_image)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val image: GridItem = arguments!!.getParcelable(IMAGE_BUNDLE_KEY) //todo better handle null

        url = image.image ?: "" //todo deal with
    }

    override fun onResume() {
        super.onResume()

        Picasso.with(activity).load(url).placeholder(R.drawable.ic_launcher_background)
            .into(ivImage)
    }

    companion object {
        const val IMAGE_BUNDLE_KEY = "image"
    }
}
