package nu.westlin.layered.persistence

import java.time.LocalDateTime

data class ToDoList(
    @get:org.springframework.data.annotation.Id
    val id: Int = 0,
    val name: String,
    val items: Set<ToDoListItem>,
    val userId: Int,
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
