package com.ikkoy.githubuserapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikkoy.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private val list = ArrayList<GitUser>()
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Github User")

        rvUser = findViewById(R.id.rv_gituser)
        rvUser.setHasFixedSize(true)

        list.addAll(listUser)
        showRecyclerList()

    }


    private val listUser: ArrayList<GitUser> @SuppressLint("Recycle")
    get(){
        val dataRealName = resources.getStringArray(R.array.realname)
        val dataUserName = resources.getStringArray(R.array.username)
        val dataFollower = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataPhoto = resources.obtainTypedArray(R.array.photo)
        val dataRepository = resources.getStringArray(R.array.repository)


        val listUsers = ArrayList<GitUser>()
        for (i in dataRealName.indices){
            val usergit = GitUser(
                dataRealName[i],
                dataUserName[i],
                dataFollower[i],
                dataFollowing[i],
                dataRepository[i],
                dataCompany[i],
                dataLocation[i],
                dataPhoto.getResourceId(i,-1))
            listUsers.add(usergit)
        }

        return listUsers

    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUser.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvUser.layoutManager = LinearLayoutManager(this)
        }
        val gitUserAdapter = GitUserAdapter(list)
        rvUser.adapter = gitUserAdapter

        gitUserAdapter.setOnItemClickCallback(object : GitUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GitUser) {
                val intentToDetailed = Intent(this@MainActivity,DetailedUserGit::class.java)
                intentToDetailed.putExtra(DetailedUserGit.DETAILED_USER,data)
                startActivity(intentToDetailed)
            }

        })
    }
}