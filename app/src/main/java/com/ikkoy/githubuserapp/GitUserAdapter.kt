package com.ikkoy.githubuserapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ikkoy.githubuserapp.databinding.GitUserRowBinding

class GitUserAdapter(private val listUser: ArrayList<GitUser>): RecyclerView.Adapter<GitUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = GitUserRowBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listUser[position])}
    }

    override fun getItemCount(): Int = listUser.size


    class ListViewHolder(private var binding: GitUserRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: GitUser){
            binding.tvItemRealName.text = user.name
            binding.tvItemUsername.text = user.username
            binding.tvItemUserLocation.text = user.location
            Glide.with(binding.root)
                .load(user.photo)
                .circleCrop()
                .into(binding.imgItemPhoto)
        }


    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GitUser)

    }



}

