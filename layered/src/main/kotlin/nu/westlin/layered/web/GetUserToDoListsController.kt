package nu.westlin.layered.web

import nu.westlin.layered.domain.GetUserToDoLists
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
