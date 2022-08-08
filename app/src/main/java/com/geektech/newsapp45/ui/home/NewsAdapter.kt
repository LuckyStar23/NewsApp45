package com.geektech.newsapp45.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.newsapp45.databinding.ItemNewsBinding
import com.geektech.newsapp45.models.News
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(private val onClick: (position: Int) ->Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var data = arrayListOf<News>()

    var onLongClick: ((position: Int) -> Unit)? = null

    inner class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.timeText.text = time()
            binding.textTitle.text = news.title
            binding.root.setOnClickListener {
                onClick.invoke(adapterPosition)
            }

            binding.root.setOnLongClickListener {
                onLongClick?.invoke(adapterPosition)
                return@setOnLongClickListener true
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount() = data.size



/*    fun addItem(news: List<News>) {
        data.add(0, )
        notifyDataSetChanged()
    }*/



    fun deleteItem(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeItem(it: Int) {

    }

    fun time(): String {
    return SimpleDateFormat("hh:mm,dd MMMM yyyy", Locale.getDefault()).format(Date())
    }

    fun getItem(pos: Int): News {
        return data[pos]
    }

    fun addItem(list: News) {
        list?.let {
            data.add(0, it)
            notifyItemInserted(data.lastIndexOf(list))
        }

    }

    fun setList(list: ArrayList<News>){
        this.data = list
        notifyDataSetChanged()
    }




}




