package com.example.reposearch.repository

import androidx.paging.PagingData
import com.example.reposearch.repository.model.DetailedRepo
import com.example.reposearch.repository.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoSearchRepository {
    suspend fun searchRepository(query: String): Flow<PagingData<Repo>>

    suspend fun getRepository(id: Int): DetailedRepo?
}