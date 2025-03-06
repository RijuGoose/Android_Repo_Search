package com.example.reposearch.ui.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.reposearch.repository.model.Repo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.reposearch.R
import com.example.reposearch.ui.search.composable.FavScreenBody
import com.example.reposearch.ui.search.composable.SearchScreenBody


@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onRepoClicked: (Repo) -> Unit
) {
    val searchText by viewModel.queryText.collectAsStateWithLifecycle()
    val repoList: LazyPagingItems<Repo> = viewModel.repoList.collectAsLazyPagingItems()
    val favouriteRepoList by viewModel.favouriteRepoList.collectAsStateWithLifecycle()
    val showFavs by viewModel.showFavs.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::changeScreen
            ) {
                if (showFavs) {
                    Text(stringResource(R.string.screen_search_fab_search))
                } else {
                    Text(stringResource(R.string.screen_search_fab_favourites))
                }
            }
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) {
        AnimatedContent(
            targetState = showFavs,
            modifier = Modifier.padding(it),
            transitionSpec = {
                if (targetState) {
                    slideInHorizontally(initialOffsetX = { it }).togetherWith(
                        slideOutHorizontally(
                            targetOffsetX = { -it })
                    )
                } else {
                    slideInHorizontally(initialOffsetX = { -it }).togetherWith(
                        slideOutHorizontally(
                            targetOffsetX = { it })
                    )
                }
            }
        ) {
            if (it) {
                FavScreenBody(
                    favList = favouriteRepoList,
                    onRepoClicked = onRepoClicked
                )
            } else {
                SearchScreenBody(
                    repoList = repoList,
                    searchText = searchText,
                    onSearchTextChange = viewModel::setQueryText,
                    onSearch = viewModel::searchRepository,
                    onRepoClicked = onRepoClicked,
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }
}
