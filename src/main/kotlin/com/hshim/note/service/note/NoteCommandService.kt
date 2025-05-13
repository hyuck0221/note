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

    private val CODE_CHARS: List<Char> = ('A'..'Z') + ('0'..'9')
    private val CODE_LENGTH = 8

    private fun generateRandomCode(): String =
        (1..CODE_LENGTH)
            .map { CODE_CHARS.random() }
            .joinToString("")

    fun init(code: String?): NoteResponse {
        var note = code?.let { noteRepository.findByIdOrNull(it) }
        if (note == null) {
            var code = code ?: generateRandomCode()
            while (noteRepository.existsById(code)) {
                code = generateRandomCode()
            }
            note = noteRepository.save(Note(code))
        }
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