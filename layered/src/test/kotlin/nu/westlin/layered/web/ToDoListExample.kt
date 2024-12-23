package nu.westlin.layered.web

import nu.westlin.layered.randomAlphabeticString
import java.time.LocalDateTime
import kotlin.random.Random

fun ToDoList.Companion.example(
    name: String = randomAlphabeticString(30),
    items: Set<ToDoListItem> = emptySet(),
    reminder: LocalDateTime? = null
): ToDoList = ToDoList(
    name = name,
    items = items,
    reminder = reminder,
)

fun ToDoListItem.Companion.example(
    name: String = randomAlphabeticString(30)
): ToDoListItem = ToDoListItem(
    name = name
)

fun CreateToDoList.Companion.example(
    name: String = randomAlphabeticString(20),
    items: Set<ToDoListItem> = emptySet(),
    userId: Int = Random.nextInt(),
    reminder: LocalDateTime? = null
): CreateToDoList = CreateToDoList(
    name = name,
    items = items,
    userId = userId,
    reminder = reminder,
)