package com.example.reposearch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.reposearch.repository.AccountRepository
import com.example.reposearch.repository.RepoSearchRepository
import com.example.reposearch.repository.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repoSearchRepository: RepoSearchRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val mutableQueryText = MutableStateFlow("")
    val queryText = mutableQueryText.asStateFlow()

    private val mutableRepoList: MutableStateFlow<PagingData<Repo>> =
        MutableStateFlow(PagingData.empty())
    val repoList = mutableRepoList.asStateFlow()

    val favouriteRepoList = accountRepository.favouriteRepoList.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    private val mutableShowFavs = MutableStateFlow(false)
    val showFavs = mutableShowFavs.asStateFlow()

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
        mutableQueryText.value = text
    }

    fun changeScreen() {
        mutableShowFavs.value = !showFavs.value
    }
}
