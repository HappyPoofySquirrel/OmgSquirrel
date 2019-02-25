package com.guyverhopkins.omgsquirrel.ui.sounds

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.guyverhopkins.omgsquirrel.R
import com.guyverhopkins.omgsquirrel.core.SoundPlayer
import kotlinx.android.synthetic.main.sounds_fragment.*

class SoundsFragment : Fragment() {

    private lateinit var viewModel: SoundsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sounds_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val soundPool = SoundPool.Builder().setMaxStreams(1).build()
        viewModel = ViewModelProviders.of(this, SoundsViewModelFactory(SoundPlayer(activity))).get(SoundsViewModel::class.java)

        btn_bark.setOnClickListener {
            viewModel.onBarkPressed()
        }

        // TODO: Use the ViewModel
    }

}
