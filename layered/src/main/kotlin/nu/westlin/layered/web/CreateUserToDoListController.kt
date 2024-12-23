package nu.westlin.layered.web

import nu.westlin.layered.domain.CreateUserToDoList
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime

@RestController
@RequestMapping(path = ["/todolists"])
class CreateUserToDoListController(
    private val createUserToDoList: CreateUserToDoList
) {

    @PostMapping("")
    fun todolists(@RequestBody toDoList: CreateToDoList): ResponseEntity<Unit> {
        val created = createUserToDoList.create(toDoList.toPersistence())
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id)
                .toUri()
        ).build()
    }
}

data class CreateToDoList(
    val name: String,
    val items: Set<ToDoListItem>,
    val userId: Int,
    val reminder: LocalDateTime?
)

private fun CreateToDoList.toPersistence(): nu.westlin.layered.persistence.ToDoList =
    nu.westlin.layered.persistence.ToDoList(
        name = this.name,
        items = this.items.toPersistence(),
        userId = this.userId,
        reminder = this.reminder,
    )

private fun Set<ToDoListItem>.toPersistence(): Set<nu.westlin.layered.persistence.ToDoListItem> =
    this.map { persistenceToDoListItem ->
        nu.westlin.layered.persistence.ToDoListItem(
            name = persistenceToDoListItem.name,
        )
    }.toSet()
