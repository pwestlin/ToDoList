package nu.westlin.layered.web

import nu.westlin.layered.domain.GetUserToDoLists
import nu.westlin.layered.persistence.ToDoListItem
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/todolists"])
class GetUserToDoListsController(
    private val getUserTodoLists: GetUserToDoLists
) {

    @GetMapping("/{id}")
    fun getList(@PathVariable id: Int): ResponseEntity<ToDoList?> {
        val list = getUserTodoLists.list(listId = id)
        return if (list != null) {
            ResponseEntity.ok(list.toPresentation())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/user/{userId}")
    fun todolists(@PathVariable userId: Int): Set<ToDoList> = getUserTodoLists.lists(userId).toPresentation()
}

@JvmName("Set_PersistenceToDoList_toPresentation")
private fun Set<nu.westlin.layered.persistence.ToDoList>.toPresentation(): Set<ToDoList> =
    this.map { persistenceToDoList ->
        persistenceToDoList.toPresentation()
    }.toSet()

private fun nu.westlin.layered.persistence.ToDoList.toPresentation(): ToDoList = ToDoList(
    name = this.name,
    items = this.items.toPresentation(),
    reminder = this.reminder,
)

@JvmName("Set_PersistenceToDoListItem_toPresentation")
private fun Set<ToDoListItem>.toPresentation(): Set<nu.westlin.layered.web.ToDoListItem> =
    this.map { persistenceToDoListItem ->
        nu.westlin.layered.web.ToDoListItem(
            name = persistenceToDoListItem.name,
        )
    }.toSet()
