package com.geektech.newsapp45.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp45.App
import com.geektech.newsapp45.NewsFragment.Companion.NEWS
import com.geektech.newsapp45.NewsFragment.Companion.RK_KEY
import com.geektech.newsapp45.R
import com.geektech.newsapp45.databinding.FragmentHomeBinding
import com.geektech.newsapp45.models.News

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter
    private var list = arrayListOf<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = (App.database.newsDao().getAll2() as ArrayList<News>)
        adapter = NewsAdapter(list)
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

        list = (App.database.newsDao().getAll2() as ArrayList<News>)

        alertDialog()

        setTime()
        search()

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newsFragment)
        }

        parentFragmentManager.setFragmentResultListener(
            RK_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            val news = bundle.getSerializable(NEWS) as News
            if (news.title == "Ser") Toast.makeText(requireContext(), "sada", Toast.LENGTH_LONG)
                .show()

            adapter.setList(list)

            binding.recyclerView.adapter = adapter
            Log.e("Home", "text: $news")
            Log.e("Home", "text: ${news.title}")
        }

        binding.recyclerView.adapter = adapter
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val list = newText?.let { App.database.newsDao().getSearch(it) }
                adapter.setList(list as ArrayList<News>)
                return false
            }
        })
    }

    private fun setTime() {

    }

    private fun alertDialog() {
        adapter.onLongClick = {
            val dialog = context?.let { AlertDialog.Builder(it) }
            dialog?.setTitle("Удалить объект")
            dialog?.setMessage("Вы хотите удалить объект?")
            dialog?.setPositiveButton("Да, удалить объект") { _, _ ->
                val news = adapter.getItem(it)
                Toast.makeText(requireContext(), news.createAt.toString(), Toast.LENGTH_SHORT).show()
                adapter.deleteItem(position = it)
                binding.recyclerView.adapter = adapter
            }
            binding.recyclerView.adapter = adapter

            dialog?.setNegativeButton("Закрыть") { dialogCancel, _ -> dialogCancel.cancel() }
            dialog?.show()
        }
    }
}