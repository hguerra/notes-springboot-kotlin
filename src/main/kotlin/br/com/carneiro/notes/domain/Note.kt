package br.com.carneiro.notes.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Note(
    @Id
    val id: String? = null,
    var text: String? = null,
    @JsonIgnore
    var user: String? = null
)
