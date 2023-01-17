package com.r42914lg.motiontest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.r42914lg.motiontest.databinding.FragmentEndTransitionBinding

class EndTransitionFragment constructor(private val title: String) : Fragment() {

    private var _binding: FragmentEndTransitionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = title
        binding.close.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}