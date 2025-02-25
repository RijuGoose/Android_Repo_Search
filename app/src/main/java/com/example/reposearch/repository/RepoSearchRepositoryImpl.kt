package com.example.reposearch.repository

import com.example.reposearch.remote.RepoSearchService
import com.example.reposearch.repository.mapper.toDomainModel
import com.example.reposearch.repository.model.DetailedRepo
import com.example.reposearch.repository.model.RepoResult
import javax.inject.Inject

class RepoSearchRepositoryImpl @Inject constructor(
    private val repoSearchService: RepoSearchService
) : RepoSearchRepository {
    override suspend fun searchRepository(query: String): RepoResult? {
        val response = repoSearchService.searchRepository(query).body()
        return response?.toDomainModel()
    }

    override suspend fun getRepository(id: Int): DetailedRepo? {
        val response = repoSearchService.getRepository(id).body()
        return response?.toDomainModel()
    }
}