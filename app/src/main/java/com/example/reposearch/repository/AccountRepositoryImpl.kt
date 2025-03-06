package com.example.reposearch.repository

import com.example.reposearch.remote.AccountService
import com.example.reposearch.repository.model.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountService: AccountService,
) : AccountRepository {
    private val mutableFavouriteRepoList = MutableStateFlow<List<Repo>>(emptyList())
    override val favouriteRepoList: Flow<List<Repo>> = mutableFavouriteRepoList

    override suspend fun login(user: String, password: String): Boolean {
        return accountService.login(user, password).success
    }

    override suspend fun addRepoToFavourites(repo: Repo) {
        mutableFavouriteRepoList.update {
            it + repo
        }
    }

    override suspend fun removeRepoFromFavourites(repo: Repo) {
        mutableFavouriteRepoList.update {
            it - repo
        }
    }
}