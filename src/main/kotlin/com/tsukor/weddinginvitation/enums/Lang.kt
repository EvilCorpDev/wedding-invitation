package com.tsukor.weddinginvitation.enums

enum class Lang {
    EN, PL, UA;

    companion object {
        fun fromString(str: String): Lang {
            return entries.firstOrNull { it.name.lowercase() == str } ?: EN
        }
    }
}