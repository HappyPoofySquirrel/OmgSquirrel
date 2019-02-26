package com.guyverhopkins.omgsquirrel.ui.sounds

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer

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
        viewModel =
            ViewModelProviders.of(this, SoundsViewModelFactory(SoundPlayer(activity))).get(SoundsViewModel::class.java)

        btn_bark.setOnClickListener {
            viewModel.onBarkPressed()
        }

        fab_sound_toggle.setOnClickListener {
            viewModel.soundTogglePressed()
        }

        btn_loop.setOnClickListener {
            viewModel.OnLoopTogglePressed()
        }

        btn_stop.setOnClickListener {
            viewModel.stopAllSounds()
        }
        // TODO: Use the ViewModel

        viewModel.flag.observe(this, Observer {
            var drawableResource = R.drawable.ic_volume_up_black_24dp
            if (!it) {
                drawableResource = R.drawable.ic_volume_off_black_24dp
            }
            context?.let {
                val drawable = ContextCompat.getDrawable(
                    it,
                    drawableResource
                )
                fab_sound_toggle.setImageDrawable(drawable)
            }
        })

        viewModel.loop.observe(this, Observer {
            var text = "Loop: On"
            if (!it) {
                text = "Loop: Off"
            }
            btn_loop.text = text
        })
    }

}
