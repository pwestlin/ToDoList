package nu.westlin.layered.domain

import nu.westlin.layered.persistence.ToDoList
import nu.westlin.layered.persistence.ToDoListRepository
import org.springframework.stereotype.Service

@Service
class CreateUserToDoList(private val toDoListRepository: ToDoListRepository) {
    fun create(toDoList: ToDoList): ToDoList = toDoListRepository.save(toDoList)
}