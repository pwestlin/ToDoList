package nu.westlin.layered

import nu.westlin.layered.persistence.Id
import nu.westlin.layered.persistence.ToDoList
import nu.westlin.layered.persistence.ToDoListItem
import nu.westlin.layered.persistence.ToDoListRepository
import nu.westlin.layered.persistence.User
import nu.westlin.layered.persistence.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@SpringBootApplication
class LayeredApplication

fun main(args: Array<String>) {
    runApplication<LayeredApplication>(*args)
}

@Service
class InitData(
    private val userRepository: UserRepository,
    private val toDoListRepository: ToDoListRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        // TODO pwestlin: Use domain service and not repo for saving

        val userFoo = userRepository.save(User(id = Id(value = 0), email = "foo@foo.com"))
        val userBar = userRepository.save(User(id = Id(value = 0), email = "bar@bar.com"))

        listOf(
            ToDoList(id = Id(0), name = "First list", items = setOf(), userId = userFoo.id, reminder = null),
            ToDoList(
                id = Id(0),
                name = "Second list",
                items = setOf(),
                userId = userFoo.id,
                reminder = LocalDateTime.now().plusSeconds(30)
            ),
            ToDoList(
                id = Id(0),
                name = "A list",
                items = setOf(ToDoListItem("First"), ToDoListItem("Second")),
                userId = userBar.id,
                reminder = null
            ),
            ToDoList(
                id = Id(0),
                name = "Another list",
                items = setOf(ToDoListItem("Foo"), ToDoListItem("Bar")),
                userId = userBar.id,
                reminder = LocalDateTime.now().plusSeconds(42)
            ),
        ).forEach(toDoListRepository::save)

        println("sdfgsdfhh: ${toDoListRepository.findById(Id(1))}")
    }
}