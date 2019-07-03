package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        return when {
            fullName != null && fullName.isBlank() -> null to null
            else -> {
                val parts: List<String>? = fullName?.split(" ")
                val firstName = parts?.getOrNull(0)
                val lastName = parts?.getOrNull(1)
                firstName to lastName
            }
        }
    }
}