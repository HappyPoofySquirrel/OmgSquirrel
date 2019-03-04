package com.guyverhopkins.omgsquirrel.ui.sounds

import android.os.Bundle
import android.view.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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

        fab_pause.setOnClickListener {
            viewModel.stopSound()
        }

        viewModel.loop.observe(this, Observer {
            viewModel.stopSound()
            var drawableResource = R.drawable.ic_repeat_active_24dp
            if (!it) {
                drawableResource = R.drawable.ic_repeat_inactive_24dp
            }
            context?.let {
                val drawable = ContextCompat.getDrawable(
                    it,
                    drawableResource
                )
                menu?.getItem(0)?.setIcon(drawable)
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

    private var menu: Menu? = null

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        this.menu = menu
        inflater?.inflate(R.menu.menu_sounds_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.loop -> {
                viewModel.onLoopTogglePressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSoundPressed(position: Int) {
        viewModel.playSound(position)
    }

    override fun onFavoriteToggled(isChecked: Boolean, position: Int) {
        viewModel.onFavoriteToggled(isChecked, position)
    }
}
