package com.example.reposearch.repository.model

import java.time.LocalDateTime

data class DetailedRepo(
    val id: Int,
    val name: String,
    val description: String?,
    val owner: RepoOwner?,
    val url: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val stargazersCount: Int,
    val forksCount: Int,
)
