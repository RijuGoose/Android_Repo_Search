package com.example.reposearch.remote

import com.example.reposearch.remote.model.LoginResponse

interface AccountService {
    suspend fun login(user: String, password: String): LoginResponse
}