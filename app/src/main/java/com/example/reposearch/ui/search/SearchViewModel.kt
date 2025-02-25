package com.example.reposearch.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.reposearch.repository.RepoSearchRepository
import com.example.reposearch.repository.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repoSearchRepository: RepoSearchRepository
) : ViewModel() {
    var queryText = mutableStateOf("")
        private set

    private val mutableRepoList: MutableStateFlow<PagingData<Repo>> =
        MutableStateFlow(PagingData.empty())
    val repoList = mutableRepoList.asStateFlow()

    fun searchRepository(query: String) {
        collectSearch(query)
    }

    private fun collectSearch(query: String) {
        viewModelScope.launch {
            repoSearchRepository.searchRepository(query)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    mutableRepoList.value = it
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