package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    return when {
        this.length >= value -> """${substring(0, value).trimEnd()}..."""
        else -> this
    }
}

fun String.stripHtml(): String {
    return this
        .replace("\\s+".toRegex(), " ")
        .replace("<[^>]*>".toRegex(), "")
}