package nu.westlin.layered.domain

import nu.westlin.layered.persistence.ToDoList
import nu.westlin.layered.persistence.ToDoListRepository
import org.springframework.stereotype.Service

@Service
class GetUserToDoLists(private val toDoListRepository: ToDoListRepository) {

    fun lists(id: Int): Set<ToDoList> = toDoListRepository.findByUserId(id)
    fun list(listId: Int): ToDoList? = toDoListRepository.findById(listId).orElse(null)
}