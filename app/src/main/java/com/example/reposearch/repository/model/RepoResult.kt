package com.example.reposearch.repository.model

data class RepoResult(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<Repo>
)
