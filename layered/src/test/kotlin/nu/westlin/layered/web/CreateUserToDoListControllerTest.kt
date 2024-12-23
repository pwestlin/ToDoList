package nu.westlin.layered.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.clearAllMocks
import io.mockk.every
import nu.westlin.layered.domain.CreateUserToDoList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.assertj.MockMvcTester

@WebMvcTest(CreateUserToDoListController::class)
class CreateUserToDoListControllerTest(
    @Autowired private val mockMvcTester: MockMvcTester,
    @Autowired private val objectMapper: ObjectMapper,
) {

    @MockkBean
    private lateinit var createUserToDoList: CreateUserToDoList

    @AfterEach
    fun clearMocks() {
        clearAllMocks()
    }

    @Test
    fun `create todo list`() {
        val createToDoList = CreateToDoList.example()
        val createdToDoList = createToDoList.toPersistence().copy(id = 42)
        every { createUserToDoList.create(createToDoList.toPersistence()) } returns createdToDoList

        val result = mockMvcTester.post()
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createToDoList))
            .uri("/todolists")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()

        assertThat(result)
            .hasStatus(HttpStatus.CREATED)
            .hasHeader(HttpHeaders.LOCATION, "http://localhost/todolists/${createdToDoList.id}")
            .hasBodyTextEqualTo("")
    }
}