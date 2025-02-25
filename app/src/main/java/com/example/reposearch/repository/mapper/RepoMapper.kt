package com.example.reposearch.repository.mapper

import com.example.reposearch.remote.model.DetailedRepoResponse
import com.example.reposearch.remote.model.RepoOwnerResponse
import com.example.reposearch.remote.model.RepoSearchResponse
import com.example.reposearch.remote.model.RepoResultResponse
import com.example.reposearch.repository.model.DetailedRepo
import com.example.reposearch.repository.model.Repo
import com.example.reposearch.repository.model.RepoOwner
import com.example.reposearch.repository.model.RepoResult
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun RepoResultResponse.toDomainModel(): RepoResult =
    RepoResult(
        totalCount = totalCount,
        incompleteResults = incompleteResults,
        items = items.map { it.toDomainModel() }
    )

fun RepoSearchResponse.toDomainModel(): Repo =
    Repo(
        id = id,
        name = name,
        description = description,
        updatedAt = LocalDateTime.parse(updatedAt, DateTimeFormatter.ISO_DATE_TIME),
        stargazersCount = stargazersCount,
    )

fun RepoOwnerResponse.toDomainModel(): RepoOwner =
    RepoOwner(
        login = login,
        avatarUrl = avatarUrl,
        url = url
    )

fun DetailedRepoResponse.toDomainModel() = DetailedRepo(
    id = id,
    name = name,
    description = description,
    owner = owner?.toDomainModel(),
    url = url,
    createdAt = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME),
    updatedAt = LocalDateTime.parse(updatedAt, DateTimeFormatter.ISO_DATE_TIME),
    stargazersCount = stargazersCount,
    forksCount = forksCount,
)
