package nu.westlin.layered.persistence

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@H2DataJdbcTest
class ToDoListRepositoryTest(
    @Autowired private val toDoListRepository: ToDoListRepository,
    @Autowired private val userRepository: UserRepository,
) {

    @Test
    fun `save and find list`() {
        val user = userRepository.save(User.example())
        val list = ToDoList.example(userId = user.id)
        val saved = toDoListRepository.save(list)
        assertThat(saved).usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(list)

        assertThat(toDoListRepository.findById(saved.id).orElse(null)).isEqualTo(saved)
    }

    @Test
    fun `find lists for user`() {
        val user = userRepository.save(User.example())
        val list1 = toDoListRepository.save(ToDoList.example(userId = user.id))
        val list2 = toDoListRepository.save(ToDoList.example(userId = user.id))

        userRepository.save(User.example()).id.let { userId ->
            repeat(3) {
                toDoListRepository.save(ToDoList.example(userId = userId))
            }
        }

        assertThat(toDoListRepository.findByUserId(user.id)).containsExactlyInAnyOrder(list1, list2)
    }
}

