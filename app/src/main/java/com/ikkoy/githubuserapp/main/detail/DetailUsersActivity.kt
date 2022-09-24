package com.ikkoy.githubuserapp.main.detail

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.ikkoy.githubuserapp.R
import com.ikkoy.githubuserapp.adapter.SectionPagerAdapter
import com.ikkoy.githubuserapp.databinding.ActivityDetailUsersBinding

class DetailUsersActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2
        )
    }

    private lateinit var binding: ActivityDetailUsersBinding
    private lateinit var detailViewModel: DetailUserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setData()
    }

    private fun setData() {
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        binding.apply {
            val sectionPagerAdapter = SectionPagerAdapter(this@DetailUsersActivity, bundle)
            viewPager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TITLES[position])
            }.attach()
        }

        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailUserViewModel::class.java]
        detailViewModel.getDetail(username.toString())
        detailViewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    detailName.text = it.name
                    detailUsername.text = it.login
                    detailFollowers.text = resources.getString(R.string.followers, it.followers)
                    detailFollowing.text = resources.getString(R.string.followers, it.following)
                    detailCompany.text = it.company
                    detailLocation.text = it.location
                    Glide.with(binding.root)
                        .load(it.avatarUrl)
                        .circleCrop()
                        .into(detailAvatar)
                }
            }
        }

    }
}
