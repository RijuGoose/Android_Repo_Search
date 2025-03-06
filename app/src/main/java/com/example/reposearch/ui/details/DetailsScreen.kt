package com.example.reposearch.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reposearch.R
import com.example.reposearch.repository.model.DetailedRepo
import com.example.reposearch.repository.model.RepoOwner
import com.example.reposearch.ui.common.HyperlinkText
import com.example.reposearch.ui.common.ScreenState
import com.example.reposearch.ui.details.composable.CountDetails
import com.example.reposearch.ui.details.composable.OwnerDetails
import com.example.reposearch.ui.theme.RepoSearchTheme
import java.time.LocalDateTime
import com.example.reposearch.ui.details.composable.DateDetails
import com.example.reposearch.ui.details.composable.FavouriteButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBack: () -> Unit
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val isRepoFavourite by viewModel.isRepoFavourite.collectAsStateWithLifecycle()
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
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) {
        DetailsScreenBody(
            screenState = screenState,
            isRepoFavourite = isRepoFavourite,
            addToFavourites = viewModel::addRepoToFavourites,
            removeFromFavourites = viewModel::removeRepoFromFavourites,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
private fun DetailsScreenBody(
    screenState: ScreenState<DetailedRepo>,
    isRepoFavourite: Boolean,
    addToFavourites: (DetailedRepo) -> Unit,
    removeFromFavourites: (DetailedRepo) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .verticalScroll(rememberScrollState())
        .padding(12.dp)) {
        when (screenState) {
            is ScreenState.Error -> {
                Text(screenState.message)
            }

            is ScreenState.Success -> {
                OwnerDetails(screenState.value.owner)
                HyperlinkText(
                    hyperlink = screenState.value.url,
                    text = screenState.value.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(screenState.value.description ?: "-")
                DateDetails(
                    createdAt = screenState.value.createdAt,
                    updatedAt = screenState.value.updatedAt
                )
                CountDetails(
                    forksCount = screenState.value.forksCount,
                    stargazersCount = screenState.value.stargazersCount
                )

                if (isRepoFavourite) {
                    FavouriteButton(
                        icon = Icons.Default.Favorite,
                        onClick = {
                            removeFromFavourites(screenState.value)
                        }
                    )
                } else {
                    FavouriteButton(
                        icon = Icons.Default.FavoriteBorder,
                        onClick = {
                            addToFavourites(screenState.value)
                        }
                    )
                }
            }

            else -> {
                /* no-op */
            }
        }

    }
}

@Preview(showBackground = true)
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
            addToFavourites = {},
            removeFromFavourites = {},
            isRepoFavourite = false
        )
    }
}