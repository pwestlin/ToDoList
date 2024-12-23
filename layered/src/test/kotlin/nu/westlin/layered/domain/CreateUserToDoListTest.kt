package nu.westlin.layered.domain

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import nu.westlin.layered.persistence.ToDoList
import nu.westlin.layered.persistence.ToDoListItem
import nu.westlin.layered.persistence.ToDoListRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CreateUserToDoListTest {

    private val toDoListRepository: ToDoListRepository = mockk()

    private val createUserToDoList = CreateUserToDoList(toDoListRepository = toDoListRepository)

    @AfterEach
    fun clearMocks() {
        clearAllMocks()
    }

    @Test
    fun `create list`() {
        val toDoList = ToDoList(
            id = 0,
            name = "Foo",
            items = setOf(ToDoListItem(name = "Bar")),
            userId = 36,
            reminder = LocalDateTime.now()
        )
        val created = toDoList.copy(id = 43)
        every { toDoListRepository.save(toDoList) } returns created

        assertThat(createUserToDoList.create(toDoList)).isEqualTo(created)
    }
}