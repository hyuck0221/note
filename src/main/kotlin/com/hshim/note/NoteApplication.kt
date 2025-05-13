package com.hshim.note

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class NoteApplication

fun main(args: Array<String>) {
    runApplication<NoteApplication>(*args)
}
