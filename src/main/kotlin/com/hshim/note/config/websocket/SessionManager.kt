package com.hshim.note.config.websocket

import com.hshim.note.model.websocket.BaseMessageModel
import com.hshim.note.model.websocket.NoteMessageResponse
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import util.ClassUtil.classToJson
import java.util.concurrent.ConcurrentHashMap

@Component
class SessionManager() {
    val sessionGroups = ConcurrentHashMap<String, MutableSet<WebSocketSession>>()
    val editSessionGroups = ConcurrentHashMap<String, MutableSet<WebSocketSession>>()

    fun addSession(code: String, session: WebSocketSession) {
        removeSession(session)
        sessionGroups.computeIfAbsent(code) { ConcurrentHashMap.newKeySet() }.add(session)
        val userCnt = getSessionCnt(code)
        send(code, NoteMessageResponse.UserCntNoteMessage(userCnt))
        if (isEditing(code)) send(session, NoteMessageResponse.EditNoteMessage())
    }

    fun addEditSession(code: String, session: WebSocketSession) {
        editSessionGroups.computeIfAbsent(code) { ConcurrentHashMap.newKeySet() }.add(session)
        sendAnother(session, code, NoteMessageResponse.EditNoteMessage())
    }

    fun removeEditSession(code: String, session: WebSocketSession) {
        editSessionGroups[code]?.remove(session)
    }

    fun removeSession(session: WebSocketSession) {
        val code = findCode(session) ?: return
        sessionGroups[code]?.remove(session)
        editSessionGroups[code]?.remove(session)
        when (val userCnt = getSessionCnt(code)) {
            0 -> sessionGroups.remove(code)
            else -> send(code, NoteMessageResponse.UserCntNoteMessage(userCnt))
        }
    }

    fun send(code: String, event: BaseMessageModel) = sessionGroups[code]?.forEach { send(it, event) }
    fun send(session: WebSocketSession, event: BaseMessageModel) {
        if (session.isOpen) session.sendMessage(TextMessage(event.classToJson()))
    }

    fun sendAnother(session: WebSocketSession, code: String, event: BaseMessageModel) {
        sessionGroups[code]?.forEach { if (it != session) send(it, event) }
    }

    fun findCode(session: WebSocketSession) = sessionGroups.filterValues { it.contains(session) }.keys.firstOrNull()
    fun getSessionCnt(code: String) = sessionGroups[code]?.size ?: 0
    fun isEditing(code: String) = editSessionGroups[code]?.isNotEmpty() ?: false
}