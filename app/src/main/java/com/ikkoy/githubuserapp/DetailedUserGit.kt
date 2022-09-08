package com.ikkoy.githubuserapp


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ikkoy.githubuserapp.databinding.ActivityDetailedUserGitBinding



class DetailedUserGit : AppCompatActivity(){

    private lateinit var binding : ActivityDetailedUserGitBinding

    companion object{
        const val DETAILED_USER = "detailed_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedUserGitBinding.inflate(layoutInflater)
        setTitle("Detailed")
        setContentView(binding.root)
        setData()

    }


    @SuppressLint("SetTextI18n")
    private fun setData(){
        val gitUser = intent.getParcelableExtra<GitUser>(DETAILED_USER) as GitUser
        with(binding){
            Glide.with(root)
                .load(gitUser.photo)
                .circleCrop()
                .into(binding.imgItemPhoto)
            tvItemRealName.text = gitUser.name
            tvItemUsername.text = "@"+gitUser.username
            tvItemUserFollower.text = gitUser.follower + " Following"
            tvItemUserFollowing.text = gitUser.following + " Followers"
            tvItemUserRepository.text = gitUser.repository + " Repository"
            tvItemUserCompany.text = gitUser.company
            tvItemUserLocation.text = gitUser.location

        }

    }
}