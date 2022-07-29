package com.geektech.newsapp45

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp45.databinding.FragmentNewsBinding
import com.geektech.newsapp45.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var msettings: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLauncher()
        binding.imageViewAvatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            launcher.launch(intent)
        }

    }

    private fun initLauncher() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                val image = it.data?.data
                if (image != null) {
                    binding.imageViewAvatar.setImageURI(image)
                }



                }
            }
        }
    private fun save() {
        Prefs(requireContext()).saveImage()
        findNavController().navigateUp()
    }}





