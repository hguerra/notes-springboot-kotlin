package br.com.carneiro.notes.web.rest

import br.com.carneiro.notes.domain.Note
import br.com.carneiro.notes.repository.NotesRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class HomeResource(val repository: NotesRepository) {

    @GetMapping("/")
    fun home(principal: Principal) : Iterable<Note> {
        println("Fetching notes for user: ${principal.name}")
        val notes = repository.findAllByUser(principal.name)

        if (notes.any()) {
            return notes
        }

        return listOf()
    }
}
