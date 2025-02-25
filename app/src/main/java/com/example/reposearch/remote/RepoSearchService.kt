package com.example.reposearch.remote

import com.example.reposearch.remote.model.DetailedRepoResponse
import com.example.reposearch.remote.model.RepoResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoSearchService {
    @GET("search/repositories")
    suspend fun searchRepository(@Query("q") query: String): Response<RepoResultResponse>

    @GET("repositories/{id}")
    suspend fun getRepository(@Path("id") id: Int): Response<DetailedRepoResponse>
}