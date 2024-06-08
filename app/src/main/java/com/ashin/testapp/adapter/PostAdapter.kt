package com.ashin.testapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashin.testapp.databinding.EachRowBinding
import com.ashin.testapp.model.Post

class PostAdapter(private var postList: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    lateinit var binding: EachRowBinding
    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding=EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
     return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.tasks.text=postList[position].body
    }
    fun addAllData(data: List<Post>)
    {
        this.postList=postList
        notifyDataSetChanged()
    }
}