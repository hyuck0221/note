package com.hshim.note.database.note.repository

import com.hshim.note.database.note.Note
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, String> {
    fun findAllByCodeInOrderByCreateDateDesc(codes: List<String>): List<Note>
}