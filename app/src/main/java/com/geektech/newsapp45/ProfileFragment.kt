package com.geektech.newsapp45

import android.R.attr.data
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.geektech.newsapp45.databinding.FragmentProfileBinding
import java.lang.String


class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var prefs: Prefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(requireContext())
        if (prefs.getImage() != null) {
            binding.imageViewAvatar.setImageURI(Uri.parse(prefs.getImage()))
        }

        initLauncher()

        binding.imageViewAvatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            launcher.launch(intent)
        }

    }

    private fun initLauncher() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                    val image = it.data?.data
                    if (image != null) {
                        requireActivity().grantUriPermission(requireActivity().packageName, image, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        requireActivity().contentResolver.takePersistableUriPermission(image, takeFlags)

                        prefs.setImage(image.toString())
                        binding.imageViewAvatar.setImageURI(image)
                    }
                }
            }
    }
}





