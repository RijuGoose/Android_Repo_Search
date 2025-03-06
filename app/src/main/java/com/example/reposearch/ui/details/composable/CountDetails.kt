package com.example.reposearch.ui.details.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ForkRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.reposearch.R

@Composable
fun CountDetails(stargazersCount: Int, forksCount: Int) {
    Row {
        Icon(
            Icons.Default.ForkRight,
            contentDescription = stringResource(R.string.screen_details_forks)
        )
        Text(forksCount.toString())
    }
    Row {
        Icon(
            Icons.Default.Star,
            contentDescription = stringResource(R.string.icon_description_stars)
        )
        Text(stargazersCount.toString())
    }
}