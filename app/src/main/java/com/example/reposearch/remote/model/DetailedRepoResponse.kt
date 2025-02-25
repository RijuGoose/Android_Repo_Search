package com.example.reposearch.remote.model

import com.google.gson.annotations.SerializedName

data class DetailedRepoResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val owner: RepoOwnerResponse?,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
)