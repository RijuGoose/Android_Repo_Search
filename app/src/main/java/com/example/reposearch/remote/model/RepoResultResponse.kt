package com.example.reposearch.remote.model

data class RepoResultResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<RepoSearchResponse>
)
