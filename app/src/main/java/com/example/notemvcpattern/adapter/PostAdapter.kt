package com.example.notemvcpattern.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notemvcpattern.MainActivity
import com.example.notemvcpattern.R
import com.example.notemvcpattern.databinding.ItemPostViewBinding
import com.example.notemvcpattern.model.PosterResp

class PostAdapter(var activit: MainActivity, var posts: ArrayList<PosterResp>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var binding: ItemPostViewBinding

    inner class ViewHolder(val binding: ItemPostViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemPostViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder){
            with(posts[position]){
                binding.tvTheme.text = this.theme
                binding.tvTitle.text = this.title
                binding.tvTime.text = this.time

                binding.tvDelete.setOnClickListener {
                    activit.postDelete(this)
                }
            }
        }
    }

    override fun getItemCount(): Int{
        return posts.size
    }
}