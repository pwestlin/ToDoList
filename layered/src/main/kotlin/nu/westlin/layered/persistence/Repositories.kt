package nu.westlin.layered.persistence

import org.springframework.data.repository.CrudRepository

interface ToDoListRepository : CrudRepository<ToDoList, Int> {
    fun findByUserId(userId: Int): Set<ToDoList>
}

interface UserRepository : CrudRepository<User, Int>