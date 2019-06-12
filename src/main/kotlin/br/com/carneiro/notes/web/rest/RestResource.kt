package br.com.carneiro.notes.web.rest

import br.com.carneiro.notes.domain.Note
import br.com.carneiro.notes.repository.NotesRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api")
class HomeResource(val repository: NotesRepository) {

    @GetMapping("/notes")
    fun home(principal: Principal) : Iterable<Note> {
        println("Fetching notes for user: ${principal.name}")
        val notes = repository.findAllByUser(principal.name)

        if (notes.any()) {
            return notes
        }

        return listOf()
    }

}

@RestController
@RequestMapping("/api")
class InfoResource {

    @GetMapping("/infos")
    fun info() : MutableMap<String, Any> {
        val authentication = SecurityContextHolder.getContext().authentication

        val json = mutableMapOf<String, Any>()
        json["name"] = authentication.name
        json["roles"] = authentication.authorities.map { it.authority }

        return json
    }

}
