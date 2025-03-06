package com.example.reposearch.ui.details.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.reposearch.repository.model.RepoOwner
import com.example.reposearch.ui.common.HyperlinkText

@Composable
fun OwnerDetails(repoOwner: RepoOwner?) {
    repoOwner?.let {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                model = it.avatarUrl,
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
                    text = it.login,
                    style = MaterialTheme.typography.headlineSmall
                )
                HyperlinkText(
                    hyperlink = it.url,
                    text = it.url,
                )
            }
        }
    }
}
