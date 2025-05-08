package com.hshim.note.service.note

import com.hshim.note.database.note.Note
import com.hshim.note.database.note.repository.NoteRepository
import com.hshim.note.model.note.NoteResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class NoteCommandService(private val noteRepository: NoteRepository) {
    fun init(code: String): NoteResponse {
        val note = noteRepository.findByIdOrNull(code) ?: noteRepository.save(Note(code))
        return NoteResponse(note)
    }

    fun updateTitle(code: String, title: String) {
        noteRepository.findByIdOrNull(code)
            ?.apply { this.title = title }
    }

    fun updateContent(code: String, content: String?) {
        noteRepository.findByIdOrNull(code)
            ?.apply { this.content = content ?: "" }
    }
}