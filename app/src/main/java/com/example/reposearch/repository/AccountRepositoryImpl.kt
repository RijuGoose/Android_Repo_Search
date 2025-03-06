package com.example.reposearch.repository

import com.example.reposearch.remote.AccountService
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountService: AccountService,
): AccountRepository {
    override suspend fun login(user: String, password: String): Boolean {
        return accountService.login(user, password).success
    }
}