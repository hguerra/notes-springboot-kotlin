package br.com.carneiro.notes.repository

import br.com.carneiro.notes.domain.Note
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface NotesRepository : MongoRepository<Note, String> {

    fun findAllByUser(name: String?) : Iterable<Note>

}
