package com.ikkoy.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitUser(
        var name: String,
        var username: String,
        var follower: String,
        var following: String,
        var repository: String,
        var company: String,
        var location: String,
        var photo: Int,

        ) : Parcelable {
}