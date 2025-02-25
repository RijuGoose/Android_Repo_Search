package com.example.reposearch.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reposearch.remote.RepoSearchService
import com.example.reposearch.remote.model.RepoSearchResponse

class RepoPagingSource(
    private val repoSearchService: RepoSearchService,
    private val query: String
) : PagingSource<Int, RepoSearchResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoSearchResponse> {
        return try {
            val currentPage = params.key ?: 1
            val repos = repoSearchService.searchRepository(query, currentPage).body()
            repos?.let {
                LoadResult.Page(
                    data = repos.items,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (repos.items.isEmpty()) null else currentPage + 1
                )
            } ?: LoadResult.Error(Exception("No data found"))
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoSearchResponse>): Int? {
        return state.anchorPosition
    }

}