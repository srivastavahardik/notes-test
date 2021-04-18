package com.oddlyspaced.notes.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oddlyspaced.notes.R
import com.oddlyspaced.notes.databinding.FragmentHomeBinding
import com.oddlyspaced.notes.ui.home.adapter.HeaderNotesAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displaySampleHeaders()
    }

    private fun displaySampleHeaders() {
        binding.rvHomeHeader.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvHomeHeader.adapter = HeaderNotesAdapter()
    }

}