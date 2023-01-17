package com.r42914lg.motiontest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.r42914lg.motiontest.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }

        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: List<String?>?, sharedElements: Map<String?, View?>) {
                super.onMapSharedElements(names, sharedElements)
                println(">>>> FIRST EXIT ${names.toString()} $sharedElements")
                // fired when transition starts to target
                // fired when transition returns from target
            }
        })

        binding.vpMain.adapter = MainPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
    }
}

class MainPagerAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: List<Fragment> = arrayListOf(
        StartTransitionFragment(),
        OtherFragment()
    )

    override fun getItemCount() = fragments.size
    override fun createFragment(position: Int) = fragments[position]
}