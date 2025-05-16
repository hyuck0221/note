package com.hshim.note.service.note

import com.hshim.note.database.note.Note
import com.hshim.note.database.note.repository.NoteRepository
import com.hshim.note.model.note.NoteResponse
import com.hshim.note.service.connect.client.ConnectClientCommandService
import com.hshim.note.util.CodeUtil.generateRandomCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class NoteCommandService(
    private val noteRepository: NoteRepository,
    private val connectClientCommandService: ConnectClientCommandService,
) {


    fun init(code: String?, connectCode: String?): NoteResponse {
        var note = code?.let { noteRepository.findByIdOrNull(it) }
        if (note == null) {
            var code = code ?: generateRandomCode()
            while (noteRepository.existsById(code)) {
                code = generateRandomCode()
            }
            note = noteRepository.save(Note(code))
        }

        connectCode?.let { connectClientCommandService.addCode(it, note.code) }
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