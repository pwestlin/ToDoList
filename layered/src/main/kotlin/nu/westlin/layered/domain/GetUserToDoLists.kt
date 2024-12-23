package nu.westlin.layered.domain

import nu.westlin.layered.persistence.ToDoListRepository
import org.springframework.stereotype.Service
import nu.westlin.layered.domain.ToDoList as DomainToDoList
import nu.westlin.layered.domain.ToDoListItem as DomainToDoListItem
import nu.westlin.layered.persistence.ToDoList as PersistenceToDoList
import nu.westlin.layered.persistence.ToDoListItem as PersistenceToDoListItem

@Service
class GetUserToDoLists(private val toDoListRepository: ToDoListRepository) {

    fun lists(id: Int): Set<DomainToDoList> = toDoListRepository.findByUserId(id).toDomain()

    @JvmName("Set_PersistenceToDoList_toDomain")
    private fun Set<PersistenceToDoList>.toDomain(): Set<DomainToDoList> = this.map { persistenceToDoList ->
        DomainToDoList(
            name = persistenceToDoList.name,
            items = persistenceToDoList.items.toDomain(),
            reminder = persistenceToDoList.reminder,
        )
    }.toSet()

    @JvmName("Set_PersistenceToDoListItem_toDomain")
    private fun Set<PersistenceToDoListItem>.toDomain(): Set<DomainToDoListItem> = this.map { persistenceToDoListItem ->
        DomainToDoListItem(
            name = persistenceToDoListItem.name,
        )
    }.toSet()
}