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
        listOf("Note 1", "Note 2", "Note 3").forEach {
            repository.save(Note(text=it, user = "User"))
        }

        repository.findAll().forEach {
            println(">> $it")
        }
    }

}

fun main(args: Array<String>) {
    runApplication<NotesApplication>(*args)
}
