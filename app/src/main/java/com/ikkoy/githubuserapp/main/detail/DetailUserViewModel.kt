package com.ikkoy.githubuserapp.main.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ikkoy.githubuserapp.data.DetailUserResponse
import com.ikkoy.githubuserapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {

    private val _detailUsers = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUsers

    private val usersDetail = MutableLiveData<DetailUserResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DetailUserViewModel"
    }

    fun getDetail(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiServices().getUsersDetail(username)
        client.enqueue(object :Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    usersDetail.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
               _isLoading.value = false
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getUserDetail(): LiveData<DetailUserResponse>{
        return usersDetail
    }
}