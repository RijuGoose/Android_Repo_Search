package com.example.reposearch.ui.details.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.reposearch.R
import com.example.reposearch.extensions.toPrettyString
import java.time.LocalDateTime

@Composable
fun DateDetails(
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.screen_details_created_at))
            Text(createdAt.toPrettyString())
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.screen_details_updated_at))
            Text(updatedAt.toPrettyString())
        }
    }
}