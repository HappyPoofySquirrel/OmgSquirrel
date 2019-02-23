package com.guyverhopkins.omgsquirrel.ui.facts

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.guyverhopkins.omgsquirrel.R

class FactsFragment : Fragment() {

    companion object {
        fun newInstance() = FactsFragment()
    }

    private lateinit var viewModel: FactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.facts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FactsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
