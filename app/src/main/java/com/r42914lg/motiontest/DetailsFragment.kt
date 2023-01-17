package com.r42914lg.motiontest

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.core.view.doOnAttach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.r42914lg.motiontest.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var adapter: DetailsPagerAdapter

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {

        adapter = DetailsPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.vpDetails.adapter = adapter

        binding.vpDetails.doOnAttach {
            binding.vpDetails.setCurrentItem(requireArguments().getInt("PAGE_TO_OPEN"), false)
            binding.vpDetails.transitionName = "transition_id_" + requireArguments().getInt("PAGE_TO_OPEN")

            sharedElementEnterTransition = MaterialContainerTransform().apply {
                drawingViewId = R.id.nav_host_fragment_content_main
                scrimColor = Color.TRANSPARENT
            }

            setEnterSharedElementCallback(object : SharedElementCallback() {
                override fun onMapSharedElements(names: List<String?>?, sharedElements: Map<String?, View?>) {
                    super.onMapSharedElements(names, sharedElements)
                    println(">>>> DETAILS ENTER ${names.toString()} $sharedElements")
                }
            })
        }

        binding.vpDetails.offscreenPageLimit = adapter.itemCount
    }
}

class DetailsPagerAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: List<Fragment> = arrayListOf(
            EndTransitionFragment("Details fragment #1"),
            EndTransitionFragment("Details fragment #2"),
            EndTransitionFragment("Details fragment #3"),
        )

    override fun getItemCount() = fragments.size
    override fun createFragment(position: Int) = fragments[position]
}