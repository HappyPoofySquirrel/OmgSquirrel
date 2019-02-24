package com.guyverhopkins.omgsquirrel.ui.facts

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.facts_fragment.*


class FactsFragment : Fragment() {

    private lateinit var viewModel: FactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.guyverhopkins.omgsquirrel.R.layout.facts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FactsViewModel::class.java)

        myWebView.loadUrl("file:///android_asset/facts.html")


        myWebView.setBackgroundColor(0x29384999)
    }

}
