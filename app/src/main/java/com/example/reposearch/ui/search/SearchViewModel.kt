package com.example.reposearch.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reposearch.repository.RepoSearchRepository
import com.example.reposearch.repository.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repoSearchRepository: RepoSearchRepository
) : ViewModel() {
    var queryText = mutableStateOf("")
        private set

    private val mutableScreenState = MutableStateFlow<ScreenState<List<Repo>>>(ScreenState.Success(emptyList()))
    val screenState = mutableScreenState.asStateFlow()

    fun searchRepository(query: String) {
        mutableScreenState.value = ScreenState.Loading
        viewModelScope.launch {
            try {
                val result = repoSearchRepository.searchRepository(query)?.items ?: emptyList()
                mutableScreenState.value = ScreenState.Success(result)

            } catch (ex: Exception) {
                mutableScreenState.value = ScreenState.Error("Something bad happened: ${ex.message}")
            }
        }
    }

    fun setQueryText(text: String) {
        queryText.value = text
    }
}

sealed class ScreenState<out T : Any> {
    object Loading : ScreenState<Nothing>()
    data class Success<out T : Any>(val value: T) : ScreenState<T>()
    data class Error(val message: String) : ScreenState<Nothing>()

}