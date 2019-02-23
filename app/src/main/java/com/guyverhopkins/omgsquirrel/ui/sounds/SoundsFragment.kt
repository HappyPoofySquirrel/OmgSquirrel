package com.guyverhopkins.omgsquirrel.ui.sounds

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.guyverhopkins.omgsquirrel.R

class SoundsFragment : Fragment() {

    companion object {
        fun newInstance() = SoundsFragment()
    }

    private lateinit var viewModel: SoundsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sounds_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SoundsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
