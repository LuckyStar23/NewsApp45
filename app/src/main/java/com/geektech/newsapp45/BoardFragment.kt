package com.geektech.newsapp45

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp45.databinding.FragmentBoardBinding

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = BoardAdapter{
            close()
            findNavController().navigateUp()
        }
        binding.dotsIndicator.attachTo(binding.viewPager)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        })
        binding.btnSkip.setOnClickListener {
            findNavController().navigateUp()
        }

        }

    private fun close() {
        Prefs(requireContext()).saveBoardState()
        findNavController().navigateUp()
    }





}




