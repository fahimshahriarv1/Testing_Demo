package com.fahim.testing_demo.backstacktest

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.fahim.base.BaseFragment
import com.fahim.testing_demo.R
import com.fahim.testing_demo.databinding.FragmentTestBinding

class TestFragment : BaseFragment() {
    private lateinit var binding: FragmentTestBinding
    private var serial = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val x: Int
        try {
            x = arguments?.getInt(ARG_PREVIOUS)!!
            serial = x + 1
        } catch (e: Exception) {

        }
        setTitleAndSubTitle("Fragment $serial","Fragment $serial")
        setActionAndStatusBarColor(Color.BLUE)
        binding = FragmentTestBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var ad = parentFragmentManager.backStackEntryCount
        Toast.makeText(requireContext(), "Size now $ad", Toast.LENGTH_SHORT).show()
        val list = mutableListOf<Int>()
        if (ad == null)
            ad = 0
        for (i in 1..ad) {
            list.add(i)
        }
        binding.rcStacks.adapter = Adapter(list.asReversed())

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.go_next, bundleOf(ARG_PREVIOUS to serial))
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {
        const val ARG_PREVIOUS = "ARG_PREVIOUS"
    }
}