package com.ikkoy.githubuserapp.data

import com.google.gson.annotations.SerializedName

data class UserResponse(

        @field:SerializedName("login")
        val login: String,

        @field:SerializedName("html_url")
        val htmlUrl: String,

        @field:SerializedName("score")
        val score: Double,

        @field:SerializedName("avatar_url")
        val avatarUrl: String,

        @field:SerializedName("id")
        val id: Int

)