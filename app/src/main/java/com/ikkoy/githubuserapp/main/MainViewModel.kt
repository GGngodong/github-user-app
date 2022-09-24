package com.ikkoy.githubuserapp.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ikkoy.githubuserapp.data.SearchResponse
import com.ikkoy.githubuserapp.data.UserResponse
import com.ikkoy.githubuserapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _searchUser = MutableLiveData<ArrayList<UserResponse>?>()
    val searchUser: LiveData<ArrayList<UserResponse>?> = _searchUser


    private val listUsers = MutableLiveData<ArrayList<UserResponse>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        findUsers()
    }

    private fun findUsers() {
        _isLoading.value = true
        val client = ApiConfig.getApiServices().getUsers()
        client.enqueue(object : Callback<ArrayList<UserResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UserResponse>>,
                response: Response<ArrayList<UserResponse>>
            ) {

                _isLoading.value = false
                if (response.isSuccessful) {
                    listUsers.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<UserResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun searchUsers(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiServices().getUsersSearch(query)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {

                if (response.isSuccessful) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure:${t.message.toString()}")
            }

        })
    }
    fun getSearchUsers(): LiveData<ArrayList<UserResponse>>{
        return listUsers
    }
}