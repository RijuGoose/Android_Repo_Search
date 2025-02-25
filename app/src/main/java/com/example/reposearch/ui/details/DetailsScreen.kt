package com.example.reposearch.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ForkRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.reposearch.R
import com.example.reposearch.extensions.toPrettyString
import com.example.reposearch.repository.model.DetailedRepo
import com.example.reposearch.repository.model.RepoOwner
import com.example.reposearch.ui.common.HyperlinkText
import com.example.reposearch.ui.search.ScreenState
import com.example.reposearch.ui.theme.RepoSearchTheme
import java.time.LocalDateTime

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBack: () -> Unit
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    DetailsScreenBody(
        screenState = screenState,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreenBody(
    screenState: ScreenState<DetailedRepo>,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.screen_details_topbar_title)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.common_back)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(12.dp)
        ) {
            when (screenState) {
                is ScreenState.Error -> {
                    Text(screenState.message)
                }

                is ScreenState.Success -> {
                    screenState.value.owner?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            AsyncImage(
                                model = screenState.value.owner.avatarUrl,
                                modifier = Modifier
                                    .size(64.dp)
                                    .border(
                                        border = BorderStroke(
                                            width = 1.dp,
                                            color = MaterialTheme.colorScheme.onBackground
                                        ),
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape),
                                contentDescription = null
                            )
                            Column {
                                Text(
                                    text = screenState.value.owner.login,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                HyperlinkText(
                                    hyperlink = screenState.value.owner.url,
                                    text = screenState.value.owner.url,
                                )
                            }
                        }
                    }
                    HyperlinkText(
                        hyperlink = screenState.value.url,
                        text = screenState.value.name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(screenState.value.description ?: "-")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(stringResource(R.string.screen_details_created_at))
                            Text(screenState.value.createdAt.toPrettyString())
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(stringResource(R.string.screen_details_updated_at))
                            Text(screenState.value.updatedAt.toPrettyString())
                        }
                    }
                    Row {
                        Icon(
                            Icons.Default.ForkRight,
                            contentDescription = stringResource(R.string.screen_details_forks)
                        )
                        Text(screenState.value.forksCount.toString())
                    }
                    Row {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = stringResource(R.string.icon_description_stars)
                        )
                        Text(screenState.value.stargazersCount.toString())
                    }
                }

                else -> {
                    /* no-op */
                }
            }

        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun DetailsScreenPreview() {
    RepoSearchTheme {
        DetailsScreenBody(
            screenState = ScreenState.Success(
                DetailedRepo(
                    id = 1234,
                    name = "TestName",
                    description = "long description whatever lorem ipsum dolor sit amet",
                    owner = RepoOwner(
                        login = "Teszt User",
                        avatarUrl = "no avatar",
                        url = "example.com"
                    ),
                    url = "https://www.example.com",
                    createdAt = LocalDateTime.of(2023, 1, 1, 0, 0),
                    updatedAt = LocalDateTime.of(2023, 1, 1, 0, 0),
                    stargazersCount = 2345,
                    forksCount = 132,
                )
            ),
            onBack = {}
        )
    }
}