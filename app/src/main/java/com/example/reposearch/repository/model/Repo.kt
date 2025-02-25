package com.example.reposearch.repository.model

import java.time.LocalDateTime

data class Repo(
    val id: Int,
    val name: String,
    val description: String?,
    val updatedAt: LocalDateTime,
    val stargazersCount: Int,
)
