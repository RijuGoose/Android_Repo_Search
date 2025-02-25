package com.example.reposearch.remote.model

import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    val id: Int,
    val name: String,
    val description: String?,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
)