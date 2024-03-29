package br.com.carneiro.notes

import br.com.carneiro.notes.domain.Note
import br.com.carneiro.notes.repository.NotesRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class NotesApplication

@Component
class DataInitializer(val repository: NotesRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        var notes = repository.findAll()
        if (notes.isEmpty()) {
            notes = mutableListOf<Note>()
            listOf("Note 1", "Note 2", "Note 3").forEach {
                notes.add(repository.save(Note(text = it, user = "user")))
            }
        }

        notes.forEach {
            println(">> On init from MongoDB: $it")
        }
    }

}

fun main(args: Array<String>) {
    runApplication<NotesApplication>(*args)
}
