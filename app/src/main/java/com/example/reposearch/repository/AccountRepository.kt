package com.example.reposearch.repository

interface AccountRepository {
    suspend fun login(user: String, password: String): Boolean
}