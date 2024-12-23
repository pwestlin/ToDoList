@file:Suppress("unused")

package nu.westlin.layered

import java.util.*
import kotlin.streams.asSequence

private const val CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
private const val SYMBOLS = "!\"#¤%&/()=?'*'\\_-><|@£$+-"
private const val DIGITS = "0123456789"

fun randomString(length: Int, characters: Boolean = true, symbols: Boolean = true, digits: Boolean = true): String {
    require(characters || symbols || digits) { "Minst en av characters, symbols och digits måste vara true" }
    val sourceString =
        (if (characters) CHARACTERS else "") + (if (symbols) SYMBOLS else "") + (if (digits) DIGITS else "")
    return Random().ints(length.toLong(), 0, sourceString.length)
        .asSequence()
        .map(sourceString::get)
        .joinToString("")
}

fun randomAlphabeticString(length: Int) =
    randomString(length = length, characters = true, symbols = false, digits = false)

fun randomNumericString(length: Int) = randomString(length = length, characters = false, symbols = false, digits = true)

fun randomAlphaNumericString(length: Int) =
    randomString(length = length, characters = true, symbols = false, digits = true)

