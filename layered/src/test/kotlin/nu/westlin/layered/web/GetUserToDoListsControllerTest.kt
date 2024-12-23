package nu.westlin.layered.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.clearAllMocks
import io.mockk.every
import nu.westlin.layered.domain.GetUserToDoLists
import nu.westlin.layered.persistence.ToDoList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.assertj.MockMvcTester

@WebMvcTest(GetUserToDoListsController::class)
class GetUserToDoListsControllerTest(
    @Autowired private val mockMvcTester: MockMvcTester,
    @Autowired private val objectMapper: ObjectMapper,
) {

    @MockkBean
    private lateinit var getUserToDoLists: GetUserToDoLists

    @AfterEach
    fun clearMocks() {
        clearAllMocks()
    }

    @Test
    fun `get todo list - not found`() {
        val id = 42
        every { getUserToDoLists.list(id) } returns null

        val result = mockMvcTester.get()
            .uri("/todolists/$id")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
        assertThat(result)
            .hasStatus(HttpStatus.NOT_FOUND)
            .hasBodyTextEqualTo("")
    }

    @Test
    fun `get todo list - found`() {
        val toDoList = ToDoList(id = 1947, name = "Lucille Velasquez", items = setOf(), userId = 7820, reminder = null)
        every { getUserToDoLists.list(toDoList.id) } returns toDoList

        val result = mockMvcTester.get()
            .uri("/todolists/${toDoList.id}")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
        assertThat(result)
            .hasStatusOk()
            .hasContentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            .bodyText()
            .isEqualTo(objectMapper.writeValueAsString(toDoList.toPresentation()))
    }
}