package com.example.reposearch.remote.model

import com.google.gson.annotations.SerializedName

data class RepoOwnerResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val url: String,
)
