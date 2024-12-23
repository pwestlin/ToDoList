package nu.westlin.layered.web

import nu.westlin.layered.domain.GetUserToDoLists
import nu.westlin.layered.domain.ToDoList
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/users"])
class CreateUserToDoListController(
    private val getUserTodoLists: GetUserToDoLists
) {

    @GetMapping("{userId}/todolists")
    fun todolists(@PathVariable userId: Int): Set<ToDoList> = getUserTodoLists.lists(userId)
}