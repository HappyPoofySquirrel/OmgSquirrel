package com.guyverhopkins.omgsquirrel.ui.sounds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.guyverhopkins.omgsquirrel.R
import com.guyverhopkins.omgsquirrel.core.sound.SoundPlayer
import kotlinx.android.synthetic.main.sounds_fragment.*

//todo create ability to set as ringtone
// and control playback speed
//stop on second press of item have an animation playing on the cell or button that is currently playing
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

        viewModel =
            ViewModelProviders.of(
                this, SoundsViewModelFactory(
                    SoundPlayer(
                        activity
                    )
                )
            ).get(SoundsViewModel::class.java)

        btn_bark.setOnClickListener {
            viewModel.onBarkPressed()
        }

        fab_loop_toggle.setOnClickListener {
            viewModel.onLoopTogglePressed()
        }

        // TODO: Use the ViewModel

        viewModel.loop.observe(this, Observer {
            var drawableResource = R.drawable.ic_loop_green_24dp
            if (!it) {
                drawableResource = R.drawable.ic_loop_white_24dp
            }
            context?.let {
                val drawable = ContextCompat.getDrawable(
                    it,
                    drawableResource
                )
                fab_loop_toggle.setImageDrawable(drawable)
            }
        })
    }

}
