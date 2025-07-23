package com.hshim.note.api.admin.session

import com.hshim.note.config.websocket.SessionManager
import com.hshim.note.model.note.NoteResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/session")
class SessionController(private val sessionManager: SessionManager) {
    @GetMapping
    fun findAllSession(): Any {
        val sessionMap = sessionManager.sessionGroups.mapValues { it.value.map { it.id } }
        val editSessionMap = sessionManager.editSessionGroups.mapValues { it.value.map { it.id } }
        return mapOf("sessionMap" to sessionMap, "editSessionMap" to editSessionMap)
    }
}