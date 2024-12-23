package nu.westlin.layered.web

import nu.westlin.layered.persistence.ToDoList as PersistenceToDoList
import nu.westlin.layered.persistence.ToDoListItem as PersistenceToDoListItem
import nu.westlin.layered.web.ToDoList as WebToDoList
import nu.westlin.layered.web.ToDoListItem as WebToDoListItem

// TODO pwestlin: Test

@JvmName("Set_PersistenceToDoList_toPresentation")
fun Set<PersistenceToDoList>.toPresentation(): Set<WebToDoList> =
    this.map { persistenceToDoList ->
        persistenceToDoList.toPresentation()
    }.toSet()

fun PersistenceToDoList.toPresentation(): WebToDoList = WebToDoList(
    name = this.name,
    items = this.items.toPresentation(),
    reminder = this.reminder,
)

@JvmName("Set_PersistenceToDoListItem_toPresentation")
fun Set<PersistenceToDoListItem>.toPresentation(): Set<WebToDoListItem> =
    this.map { persistenceToDoListItem ->
        nu.westlin.layered.web.ToDoListItem(
            name = persistenceToDoListItem.name,
        )
    }.toSet()

// TODO pwestlin: Move and test
fun CreateToDoList.toPersistence(): PersistenceToDoList =
    PersistenceToDoList(
        name = this.name,
        items = this.items.toPersistence(),
        userId = this.userId,
        reminder = this.reminder,
    )

// TODO pwestlin: Move and test
fun Set<WebToDoListItem>.toPersistence(): Set<PersistenceToDoListItem> =
    this.map { persistenceToDoListItem ->
        PersistenceToDoListItem(
            name = persistenceToDoListItem.name,
        )
    }.toSet()
