package com.r42914lg.motiontest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.r42914lg.motiontest.databinding.FragmentStartTransitionBinding

class StartTransitionFragment : Fragment(), StoryScrollAdapter.StoryScrollListener {

    private lateinit var storyAdapter: StoryScrollAdapter

    private var _binding: FragmentStartTransitionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpStoryScrollRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpStoryScrollRecyclerView() {
        binding.rvItems.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        storyAdapter = StoryScrollAdapter(this)
        binding.rvItems.adapter = storyAdapter

        val items = listOf(0, 1, 2)
        storyAdapter.setItems(items)
    }

    override fun onClickedStoryScroll(model: Int, view: View) {
        val extras = FragmentNavigatorExtras(view to "transition_id_$model")

        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: List<String?>?, sharedElements: Map<String?, View?>) {
                super.onMapSharedElements(names, sharedElements)
                println(">>>> START EXIT TRANSITION ${names.toString()} $sharedElements")
                // this is NOT fired!!!
            }
        })

        val bundle = bundleOf("PAGE_TO_OPEN" to model)
        findNavController().navigate(R.id.detailsFragment, bundle, null, extras)
    }
}