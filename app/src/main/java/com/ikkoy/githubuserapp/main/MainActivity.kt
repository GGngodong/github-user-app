package com.ikkoy.githubuserapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikkoy.githubuserapp.UserAdapter
import com.ikkoy.githubuserapp.data.UserResponse
import com.ikkoy.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSearchView()
        supportActionBar?.hide()
        setData()

    }

    private fun setSearchView() {
        binding.apply {
            searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.searchUsers(query)
                    viewModel.searchUser.observe(this@MainActivity) { searchUser ->
                        if (searchUser != null) {
                            adapter.setList(searchUser)
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

            })
        }
    }

    private fun setData() {
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        binding.apply {
            gituserlistmain.layoutManager = LinearLayoutManager(this@MainActivity)
            gituserlistmain.setHasFixedSize(true)
            gituserlistmain.adapter = adapter
        }
        viewModel.getSearchUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
            }
        }
        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: UserResponse) {
                TODO()
            }
        })
    }
}