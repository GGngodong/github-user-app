package com.ikkoy.githubuserapp.network

import com.ikkoy.githubuserapp.data.DetailUserResponse
import com.ikkoy.githubuserapp.data.SearchResponse
import com.ikkoy.githubuserapp.data.UserResponse
import com.ikkoy.githubuserapp.token.TokenData.Companion.GIT_TOKEN
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("users")
    @Headers("Authorization: token $GIT_TOKEN")
    fun getUsers(): Call<ArrayList<UserResponse>>

    @GET("search/users")
    @Headers("Authorization: token $GIT_TOKEN")
    fun getUsersSearch(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $GIT_TOKEN")
    fun getUsersDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $GIT_TOKEN")
    fun getUsersFollowers(
        @Path("username") username: String
    ): Call<ArrayList<UserResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $GIT_TOKEN")
    fun getUsersFollowing(
        @Path("username") username: String
    ): Call<ArrayList<UserResponse>>
}