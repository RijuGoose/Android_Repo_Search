package com.example.reposearch.ui.search.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.reposearch.R
import com.example.reposearch.repository.model.Repo
import kotlinx.coroutines.launch


@Composable
fun SearchScreenBody(
    repoList: LazyPagingItems<Repo>,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onRepoClicked: (Repo) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            placeholder = { Text(stringResource(R.string.screen_search_search_textfield_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearch(searchText)
                }
            )
        )
        Button(
            onClick = {
                onSearch(searchText)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.screen_search_search_button_text))
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(
                count = repoList.itemCount,
                key = { index ->
                    repoList[index]?.id ?: 0

                }) { index ->
                val repo = repoList[index]
                repo?.let {
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
            when {
                repoList.loadState.refresh is LoadState.Error -> {
                    scope.launch {
                        snackbarHostState.showSnackbar("Something bad happened")
                    }
                }

                repoList.loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                repoList.loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
