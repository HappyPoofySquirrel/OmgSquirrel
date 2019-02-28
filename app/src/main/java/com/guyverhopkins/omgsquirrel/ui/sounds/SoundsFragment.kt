package com.guyverhopkins.omgsquirrel.ui.sounds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.guyverhopkins.omgsquirrel.R
import com.guyverhopkins.omgsquirrel.core.common.RepositoryProvider
import com.guyverhopkins.omgsquirrel.core.sound.SoundPlayer
import kotlinx.android.synthetic.main.sounds_fragment.*

//todo create ability to set as ringtone
// and control playback speed
//stop on second press of item have an animation playing on the cell or button that is currently playing
class SoundsFragment : Fragment(), SoundsAdapter.FavoriteToggleListener, SoundsAdapter.ItemClickListener {

    private lateinit var viewModel: SoundsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sounds_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.application?.let {
            viewModel =
                ViewModelProviders.of(
                    this, SoundsViewModelFactory(
                        RepositoryProvider.instance.getTaskRepository(it), SoundPlayer(activity)
                    )
                ).get(SoundsViewModel::class.java)
        } ?: kotlin.run {
            activity?.finish() //todo handle application being null better
        }

        rv_sounds.setOnClickListener {
            viewModel.onBarkPressed()
        }

        fab_loop_toggle.setOnClickListener {
            viewModel.onLoopTogglePressed()
        }

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

        viewModel.sounds.observe(this, Observer {
            viewModel.updateSoundPlayer(it) //todo is there a way i can observe this in the sound player class?
            val adapter = rv_sounds.adapter as SoundsAdapter
            adapter.submitList(it)
        })
        val adapter = SoundsAdapter()
        adapter.setFavorToggleListener(this)
        adapter.setItemClickListener(this)
        rv_sounds.adapter = adapter
        rv_sounds.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    override fun onSoundPressed(position: Int) {
        viewModel.playSound(position)
    }

    override fun onFavoriteToggled(isChecked: Boolean, position: Int) {
        viewModel.onFavoriteToggled(isChecked, position)
    }
}
