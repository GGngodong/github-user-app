package com.ikkoy.githubuserapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ikkoy.githubuserapp.data.UserResponse
import com.ikkoy.githubuserapp.databinding.ListUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val list = ArrayList<UserResponse>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(items: ArrayList<UserResponse>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(list[position])
        }
    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(private var binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: UserResponse) {
            binding.apply {
                gitName.text = userData.login
                gitUrls.text = userData.htmlUrl
                Glide.with(itemView)
                    .load(userData.avatarUrl)
                    .circleCrop()
                    .into(binding.gitUserAvatar)
            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: UserResponse)
    }
}
