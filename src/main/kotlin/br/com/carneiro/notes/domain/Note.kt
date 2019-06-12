package br.com.carneiro.notes.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Note(
    @Id
    val id: String? = null,
    val text: String? = null,
    val user: String? = null
)
