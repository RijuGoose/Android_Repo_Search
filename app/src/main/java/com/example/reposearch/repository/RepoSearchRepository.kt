package com.example.reposearch.repository

import com.example.reposearch.repository.model.DetailedRepo
import com.example.reposearch.repository.model.RepoResult

interface RepoSearchRepository {
    suspend fun searchRepository(query: String): RepoResult?

    suspend fun getRepository(id: Int): DetailedRepo?
}