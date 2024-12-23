package nu.westlin.layered.persistence

import nu.westlin.layered.randomAlphabeticString
import java.time.LocalDateTime
import kotlin.random.Random

fun ToDoList.Companion.example(
    id: Int = 0,
    name: String = randomAlphabeticString(30),
    items: Set<ToDoListItem> = emptySet(),
    userId: Int = Random.nextInt(),
    reminder: LocalDateTime? = null
): ToDoList = ToDoList(
    id = id,
    name = name,
    items = items,
    userId = userId,
    reminder = reminder,
)

fun ToDoListItem.Companion.example(
    name: String = randomAlphabeticString(30)
): ToDoListItem = ToDoListItem(
    name = name
)