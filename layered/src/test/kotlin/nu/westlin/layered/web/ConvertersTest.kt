package nu.westlin.layered.web

import nu.westlin.layered.persistence.example
import nu.westlin.layered.persistence.exampleWithAllAttributesSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import nu.westlin.layered.persistence.ToDoList as PersistenceToDoList

class ConvertersTest {

    @Test
    fun `PersistenceToDoList toPresentation - with defaults`() {
        val persistence = PersistenceToDoList.example()
        assertThat(persistence.toPresentation()).isEqualTo(
            ToDoList(
                name = persistence.name,
                items = persistence.items.toPresentation(),
                reminder = persistence.reminder,
            )
        )
    }

    @Test
    fun `PersistenceToDoList toPresentation - with all attributes set`() {
        val persistence = PersistenceToDoList.exampleWithAllAttributesSet()
        assertThat(persistence.toPresentation()).isEqualTo(
            ToDoList(
                name = persistence.name,
                items = persistence.items.toPresentation(),
                reminder = persistence.reminder,
            )
        )
    }


    @Test
    fun `Set PersistenceToDoList toPresentation - with defaults`() {
        val persistence = List(3) { PersistenceToDoList.example() }.toSet()
        assertThat(persistence.toPresentation()).containsExactlyInAnyOrderElementsOf(
            persistence.map { it.toPresentation() }.toSet(),
        )
    }

    @Test
    fun `Set PersistenceToDoList toPresentation - with all attributes set`() {
        val persistence = List(3) { PersistenceToDoList.exampleWithAllAttributesSet() }.toSet()
        assertThat(persistence.toPresentation()).containsExactlyInAnyOrderElementsOf(
            persistence.map { it.toPresentation() }.toSet(),
        )
    }
}