package com.hshim.note.util

object CodeUtil {
    private val CODE_CHARS: List<Char> = ('A'..'Z') + ('0'..'9')
    private val CODE_LENGTH = 8

    fun generateRandomCode(): String = (1..CODE_LENGTH)
        .map { CODE_CHARS.random() }
        .joinToString("")
}