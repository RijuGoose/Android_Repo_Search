package com.example.reposearch.ui.common

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink

@Composable
fun HyperlinkText(
    hyperlink: String,
    text: String,
    style: TextStyle = LocalTextStyle.current
) {
    val linkText = remember {
        buildAnnotatedString {
            withLink(LinkAnnotation.Url(url = hyperlink)) {
                append(text)
            }
        }
    }

    Text(
        text = linkText,
        style = style,
        textDecoration = TextDecoration.Underline,
        color = MaterialTheme.colorScheme.primary
    )
}