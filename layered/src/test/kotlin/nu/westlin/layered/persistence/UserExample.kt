package nu.westlin.layered.persistence

import nu.westlin.layered.randomAlphabeticString

fun User.Companion.example(
    id: Int = 0,
    email: String = randomAlphabeticString(15) + "@" + randomAlphabeticString(6) + ".com"
): User = User(
    id = id,
    email = email
)