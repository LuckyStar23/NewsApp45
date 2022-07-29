package com.geektech.newsapp45

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp45.databinding.FragmentHomeBinding
import com.geektech.newsapp45.databinding.FragmentNewsBinding
import com.geektech.newsapp45.models.News


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val text = binding.editText.text.toString()
        val news = News(0, text, System.currentTimeMillis())
        App.database.newsDao().insert(news)
        val bundle = Bundle()
        bundle.putSerializable(NEWS, news)
        parentFragmentManager.setFragmentResult(RK_KEY, bundle)
        findNavController().navigateUp()
    }

    companion object {
        const val NEWS = "news"
        const val RK_KEY = "rk_new"
    }
}

