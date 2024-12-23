package nu.westlin.layered.persistence

import org.springframework.data.relational.core.mapping.Table

@Table("APPLICATION_USER")
data class User(
    @get: org.springframework.data.annotation.Id
    val id: Int,
    val email: String
) {
    init {
        require(email.isNotBlank()) { "Email cannot be blank" }
    }

    companion object
}
