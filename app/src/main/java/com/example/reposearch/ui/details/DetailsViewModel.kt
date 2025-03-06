package com.example.reposearch.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.reposearch.Screen
import com.example.reposearch.repository.RepoSearchRepository
import com.example.reposearch.repository.model.DetailedRepo
import com.example.reposearch.ui.common.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repoSearchRepository: RepoSearchRepository
) : ViewModel() {
    private val repoId = savedStateHandle.toRoute<Screen.Details>().repoId

    private val mutableScreenState =
        MutableStateFlow<ScreenState<DetailedRepo>>(ScreenState.Loading)
    val screenState = mutableScreenState.asStateFlow()

    init {
        getRepository(repoId)
    }

    fun getRepository(id: Int) {
        viewModelScope.launch {
            try {
                val result = repoSearchRepository.getRepository(id)
                result?.let {
                    mutableScreenState.value = ScreenState.Success(it)
                } ?: run {
                    mutableScreenState.value = ScreenState.Error("Something bad happened")
                }

            } catch (ex: Exception) {
                mutableScreenState.value = ScreenState.Error("Something bad happened: ${ex.message}")
            }
        }

    }
}