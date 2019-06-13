package br.com.carneiro.notes.web.rest

import br.com.carneiro.notes.domain.Note
import br.com.carneiro.notes.repository.NotesRepository
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.oidc.user.OidcUser
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
class UserResource {

    @GetMapping("/users")
    fun home(@AuthenticationPrincipal oidcUser: OidcUser): MutableMap<String, Any> {
        val json = mutableMapOf<String, Any>()
        json["name"] = oidcUser.fullName
        return json
    }

    @GetMapping("/users/attributes")
    fun attributes(@AuthenticationPrincipal oidcUser: OidcUser): MutableMap<String, Any> {
        val json = mutableMapOf<String, Any>()
        json["attributes"] = oidcUser.attributes.toString()
        return json
    }

    @GetMapping("/users/authorities")
    fun authorities(@AuthenticationPrincipal oidcUser: OidcUser): MutableMap<String, Any> {
        val json = mutableMapOf<String, Any>()
        json["authorities"] = oidcUser.authorities.toString()
        return json
    }

    @GetMapping("/users/roles")
    fun roles() : MutableMap<String, Any> {
        val authentication = SecurityContextHolder.getContext().authentication

        val json = mutableMapOf<String, Any>()
        json["name"] = authentication.name
        json["roles"] = authentication.authorities.map { it.authority }

        return json
    }

}
