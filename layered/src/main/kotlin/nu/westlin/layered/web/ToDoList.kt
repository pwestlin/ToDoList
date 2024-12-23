package nu.westlin.layered.web

import java.time.LocalDateTime

data class ToDoList(
    val name: String,
    val items: Set<ToDoListItem>,
    val reminder: LocalDateTime?
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
    }

    companion object
}

@JvmInline
value class ToDoListItem(val name: String) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
    }

    companion object
}
