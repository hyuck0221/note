package com.hshim.note.service.note

import com.hshim.note.database.note.repository.NoteRepository
import com.hshim.note.model.note.NoteResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class NoteQueryService(private val noteRepository: NoteRepository) {
    fun findAllBy(codes: List<String>): List<NoteResponse> {
        return noteRepository.findAllByCodeInOrderByCreateDateDesc(codes).map { NoteResponse(it) }
    }
}