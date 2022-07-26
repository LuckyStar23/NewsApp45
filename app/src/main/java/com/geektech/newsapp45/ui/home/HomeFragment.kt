package com.geektech.newsapp45.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp45.NewsFragment.Companion.NEWS
import com.geektech.newsapp45.NewsFragment.Companion.RK_KEY
import com.geektech.newsapp45.R
import com.geektech.newsapp45.databinding.FragmentHomeBinding
import com.geektech.newsapp45.models.News

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter {
        }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog()

        setTime()


        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newsFragment)
        }
        parentFragmentManager.setFragmentResultListener(
            RK_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            val news = bundle.getSerializable(NEWS) as News
            if (news.title == "Ser") Toast.makeText(requireContext(), "sada", Toast.LENGTH_LONG).show()
            adapter.addItem(news)

            binding.recyclerView.adapter = adapter
            Log.e("Home", "text: $news")
            Log.e("Home", "text: ${news.title}")
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setTime() {

    }

    fun alertDialog() {
    adapter.onLongClick = {
        val dialog = context?.let { AlertDialog.Builder(it) }
        dialog?.setTitle("Удалить объект")
        dialog?.setMessage("Вы хотите удалить объект?")
        dialog?.setPositiveButton("Да, удалить объект") { _, _, ->
            val news = adapter.getItem(it)
            Toast.makeText(requireContext(), news.createAt.toString(), Toast.LENGTH_SHORT).show()
            adapter.deleteItem(position = it)
            binding.recyclerView.adapter = adapter
        }
        dialog?.setNegativeButton("Закрыть") { dialogCancel, _ -> dialogCancel.cancel() }
        dialog?.show()
}}}