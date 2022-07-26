package com.geektech.newsapp45

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.newsapp45.databinding.PagerBoardBinding

class BoardAdapter(private val onClickStart: () ->Unit) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {


    private val titles = arrayOf("Салам", "Привет", "Hello")
    private val desc = arrayOf("Кыргызча тили", "Русский яз", "English lang")
    private val image = arrayOf(R.raw.news, R.raw.newnews, R.raw.newnewnews)

    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.textTitle.text = titles[position]
            binding.textDesc.text = desc [position]
            binding.lottie.setAnimation(image[position])
            if (position == 2)
                binding.btnStart.visibility = View.VISIBLE
            else
                binding.btnStart.visibility = View.INVISIBLE
                binding.btnStart.setOnClickListener {
                    onClickStart.invoke()
                }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PagerBoardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount() = titles.size

}







