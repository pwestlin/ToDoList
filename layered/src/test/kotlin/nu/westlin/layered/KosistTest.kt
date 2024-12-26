package nu.westlin.layered

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.withAllAnnotationsOf
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import org.springframework.web.bind.annotation.RestController

// TODO pwestlin: Change the name.
//  Move to separate files?
class KosistTest {

    @Test
    fun `classes with 'RestController' annotation should have 'Repository' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(RestController::class)
            .assertTrue {
                it.hasNameEndingWith("Controller")
            }
    }

    @Test
    fun `classes with 'RestController' annotation should reside in the 'web' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(RestController::class)
            .assertTrue {
                it.resideInPackage("nu.westlin.layered.web")
            }
    }

    @Test
    fun `classes with 'Repository' annotation should have 'Repository' suffix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAllAnnotationsOf(Repository::class)
            .assertTrue { it.hasNameEndingWith("Repository") }
    }

    @Test
    fun `interfaces that extend 'CrudRepository' should have 'Repository' suffix`() {
        Konsist
            .scopeFromProduction()
            .interfaces()
            .filter {
                it.hasParentOf(name = CrudRepository::class)
            }
            .assertTrue {
                it.hasNameEndingWith("Repository")
            }
    }

    @Test
    fun `layered architecture layers have correct dependencies`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                // Define layers
                val web = Layer("Web", "nu.westlin.layered.web..")
                val domain = Layer("Domain", "nu.westlin.layered.domain..")
                val persistence = Layer("Persistence", "nu.westlin.layered.persistence..")

                // Define architecture assertions
                web.dependsOn(domain)
                domain.dependsOn(persistence)
                persistence.dependsOnNothing()
            }
    }
}