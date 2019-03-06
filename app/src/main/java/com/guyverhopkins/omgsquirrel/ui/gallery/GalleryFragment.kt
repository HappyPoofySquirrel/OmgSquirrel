package com.guyverhopkins.omgsquirrel.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.guyverhopkins.omgsquirrel.R
import com.guyverhopkins.omgsquirrel.core.gallery.FlickrImagesGetterFactory
import com.guyverhopkins.omgsquirrel.core.gallery.GridItem
import kotlinx.android.synthetic.main.gallery_fragment.*

class GalleryFragment : Fragment() {

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val flickrImagesGetter = FlickrImagesGetterFactory.build(activity!!) //todo handle null better
        viewModel =
            ViewModelProviders.of(this, GalleryViewModelFactory(flickrImagesGetter)).get(GalleryViewModel::class.java)

        val array = arrayOf(
            GridItem(),
            GridItem(),
            GridItem(),
            GridItem(),
            GridItem(),
            GridItem(),
            GridItem(),
            GridItem(),
            GridItem()
        )
        val adapter = GalleryAdapter(array)
        gv_gallery.adapter = adapter

        viewModel.getImages()
        // TODO: Use the ViewModel
    }

}
