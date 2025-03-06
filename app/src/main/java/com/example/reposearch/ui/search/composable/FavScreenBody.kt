package com.example.reposearch.ui.search.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reposearch.repository.model.Repo
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.reposearch.R

@Composable
fun FavScreenBody(
    favList: List<Repo>,
    onRepoClicked: (Repo) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize()
    ) {
        if (favList.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    items = favList,
                    key = { it.id }
                ) { repo ->
                    RepoCard(
                        name = repo.name,
                        description = repo.description ?: "-",
                        stargazersCount = repo.stargazersCount,
                        updatedAt = repo.updatedAt,
                        onItemClicked = {
                            onRepoClicked(repo)
                        }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.screen_fav_empty_text),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
