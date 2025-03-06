package com.example.reposearch.remote

import com.example.reposearch.remote.model.LoginResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(): AccountService {
    override suspend fun login(
        user: String,
        password: String
    ): LoginResponse {
        delay(2000) // Simulate network call
        return if (user != "Piller" || password != "PillerPassword") {
            LoginResponse(
                success = false
            )
        } else {
            LoginResponse(
                success = true
            )

        }
    }
}