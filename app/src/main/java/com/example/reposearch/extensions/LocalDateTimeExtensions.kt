package com.example.reposearch.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toPrettyString(): String {
    return this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
}