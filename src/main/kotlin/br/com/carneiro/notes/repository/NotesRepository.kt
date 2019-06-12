package br.com.carneiro.notes.repository

import br.com.carneiro.notes.domain.Note
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestResource
interface NotesRepository : MongoRepository<Note, String> {

    fun findAllByUser(name: String?): Iterable<Note>

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun save(s: Note): Note

}

@Component
@RepositoryEventHandler(Note::class)
class AddUserToNote {

    @HandleBeforeCreate
    fun handleCreate(note: Note) {
        val username = SecurityContextHolder.getContext().authentication.name
        println(">> Creating note: $note with user: $username")
        note.user = username
    }
}
