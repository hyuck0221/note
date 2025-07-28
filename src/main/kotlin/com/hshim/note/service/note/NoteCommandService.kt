package com.hshim.note.service.note

import com.hshim.note.database.note.Note
import com.hshim.note.database.note.repository.NoteRepository
import com.hshim.note.model.note.NoteResponse
import com.hshim.note.util.CodeUtil.generateRandomCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class NoteCommandService(private val noteRepository: NoteRepository) {

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

    fun updateTitle(code: String, title: String): NoteResponse {
        return noteRepository.findByIdOrNull(code)
            ?.apply { this.title = title }
            ?.let { NoteResponse(it) }
            ?: throw Exception("note not found.")
    }

    fun updateContent(code: String, content: String?): NoteResponse {
        return noteRepository.findByIdOrNull(code)
            ?.apply { this.content = content ?: "" }
            ?.let { NoteResponse(it) }
            ?: throw Exception("note not found.")
    }

    fun updateDescription(code: String, description: String?): NoteResponse {
        return noteRepository.findByIdOrNull(code)
            ?.apply { this.description = description ?: "" }
            ?.let { NoteResponse(it) }
            ?: throw Exception("note not found.")
    }
}