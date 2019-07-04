package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
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

    fun toInitials(firstName: String?, lastName: String?): String? {
        val first = firstName?.trim()
        val last = lastName?.trim()
        return if (first.isNullOrEmpty() && last.isNullOrEmpty()) null
        else {
            var initials = ""
            val firstNameInitial = first?.getOrNull(0)
            val lastNameInitial = last?.getOrNull(0)
            when {
                firstNameInitial != null -> initials += firstNameInitial
            }
            when {
                lastNameInitial != null -> initials += lastNameInitial
            }
            initials.toUpperCase()
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val map = mapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )
        var login = ""
        for (c in payload) {
            val charPayload: String = c.toString()
            login += when {
                charPayload == " " -> divider
                charPayload.toLowerCase() in map -> {
                    if (c.isUpperCase()) {
                        val first = map.getValue(charPayload.toLowerCase()).substring(0, 1).toUpperCase()
                        val second = map.getValue(charPayload.toLowerCase()).substring(1)
                        "$first$second"
                    } else map[charPayload]
                }
                else -> charPayload
            }
        }
        return login
    }
}