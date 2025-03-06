package com.example.reposearch.repository

import com.example.reposearch.repository.model.Repo
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    val favouriteRepoList: Flow<List<Repo>>
    suspend fun login(user: String, password: String): Boolean
    suspend fun addRepoToFavourites(repo: Repo)
    suspend fun removeRepoFromFavourites(repo: Repo)
}