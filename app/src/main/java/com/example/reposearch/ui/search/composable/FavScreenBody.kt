package com.example.reposearch.ui.search.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.reposearch.repository.model.Repo


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
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            this.items(
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
    }
}
