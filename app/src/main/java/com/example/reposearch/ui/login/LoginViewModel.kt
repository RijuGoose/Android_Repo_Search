package com.example.reposearch.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reposearch.repository.AccountRepository
import com.example.reposearch.ui.common.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val mutableUsername = MutableStateFlow("")
    val username = mutableUsername.asStateFlow()

    private val mutablePassword = MutableStateFlow("")
    val password = mutablePassword.asStateFlow()

    private val mutableScreenState = MutableStateFlow<ScreenState<Unit>>(ScreenState.Idle)
    val screenState = mutableScreenState.asStateFlow()

    fun setUsername(username: String) {
        mutableUsername.value = username
    }

    fun setPassword(password: String) {
        mutablePassword.value = password
    }

    fun login() {
        viewModelScope.launch {
            mutableScreenState.value = ScreenState.Loading
            mutableScreenState.value = try {
                val success = accountRepository.login(
                    user = mutableUsername.value,
                    password = mutablePassword.value
                )
                if (success) {
                    ScreenState.Success(Unit)
                } else {
                    ScreenState.Error("Wrong username or password")
                }
            } catch (ex: Exception) {
                ScreenState.Error("Something bad happened: ${ex.message}")
            }
        }
    }

}