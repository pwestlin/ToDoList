package nu.westlin.layered.domain

import java.time.LocalDateTime

data class ToDoList(
    val name: String,
    val items: Set<ToDoListItem>,
    val reminder: LocalDateTime?
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
    }
}

@JvmInline
value class ToDoListItem(val name: String) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
    }
}
