package com.example.reposearch.ui.search.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reposearch.R
import com.example.reposearch.extensions.toPrettyString
import com.example.reposearch.ui.theme.RepoSearchTheme
import java.time.LocalDateTime

@Composable
fun RepoCard(
    name: String,
    description: String,
    stargazersCount: Int,
    updatedAt: LocalDateTime,
    onItemClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked.invoke()
            }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    stargazersCount.toString(),
                    style = MaterialTheme.typography.headlineSmall
                )
                Icon(
                    Icons.Default.Star,
                    contentDescription = stringResource(R.string.icon_description_stars)
                )
            }
            Text("Last updated: " + updatedAt.toPrettyString())
            Text(description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview
@Composable
fun RepoCardPreview() {
    RepoSearchTheme {
        RepoCard(
            name = "testtt name long long name",
            description = "test desc lorem ipsum dolor sit amet",
            stargazersCount = 23450,
            updatedAt = LocalDateTime.of(2023, 1, 1, 1, 1),
            onItemClicked = {}
        )
    }
}