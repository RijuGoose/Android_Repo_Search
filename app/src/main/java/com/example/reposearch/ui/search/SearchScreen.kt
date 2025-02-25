package com.example.reposearch.ui.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.reposearch.R
import com.example.reposearch.repository.model.Repo
import com.example.reposearch.ui.search.composable.RepoCard
import com.example.reposearch.ui.theme.RepoSearchTheme
import java.time.LocalDateTime

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onRepoClicked: (Repo) -> Unit
) {
    val searchText = viewModel.queryText
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    SearchScreenBody(
        screenState = screenState,
        searchText = searchText.value,
        onSearchTextChange = viewModel::setQueryText,
        onSearch = viewModel::searchRepository,
        onRepoClicked = onRepoClicked
    )

    if (screenState is ScreenState.Loading) {
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun SearchScreenBody(
    screenState: ScreenState<List<Repo>>,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onRepoClicked: (Repo) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold {
        Column(
            modifier = modifier
                .padding(it)
                .padding(horizontal = 12.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                placeholder = { Text(stringResource(R.string.screen_search_search_textfield_placeholder)) },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    onSearch(searchText)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.screen_search_search_button_text))
            }

            when (screenState) {
                is ScreenState.Error -> {
                    Text(screenState.message)
                }

                is ScreenState.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = screenState.value,
                            key = { it.id }
                        ) {
                            RepoCard(
                                name = it.name,
                                description = it.description ?: "-",
                                stargazersCount = it.stargazersCount,
                                updatedAt = it.updatedAt,
                                onItemClicked = {
                                    onRepoClicked(it)
                                }
                            )
                        }
                    }
                }

                else -> {
                    /* no-op */
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    RepoSearchTheme {
        SearchScreenBody(
            searchText = "",
            onSearchTextChange = {},
            screenState = ScreenState.Success(
                List(10) {
                    Repo(
                        id = it,
                        name = "testname_$it",
                        description = "test desc",
                        updatedAt = LocalDateTime.of(2023, 1, 1, 1, 1),
                        stargazersCount = 2,
                    )
                }
            ),
            onSearch = {},
            onRepoClicked = {}
        )
    }
}