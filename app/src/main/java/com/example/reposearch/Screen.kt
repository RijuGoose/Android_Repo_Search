package com.example.reposearch

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Search : Screen()

    @Serializable
    data class Details(val repoId: Int) : Screen()
}