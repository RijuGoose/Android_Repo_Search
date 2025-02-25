package com.example.reposearch.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.reposearch.remote.RepoSearchService
import com.example.reposearch.repository.mapper.toDomainModel
import com.example.reposearch.repository.model.DetailedRepo
import com.example.reposearch.repository.model.Repo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepoSearchRepositoryImpl @Inject constructor(
    private val repoSearchService: RepoSearchService
) : RepoSearchRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun searchRepository(query: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {
                RepoPagingSource(repoSearchService, query)
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toDomainModel()
            }
        }
    }

    override suspend fun getRepository(id: Int): DetailedRepo? {
        val response = repoSearchService.getRepository(id).body()
        return response?.toDomainModel()
    }
}