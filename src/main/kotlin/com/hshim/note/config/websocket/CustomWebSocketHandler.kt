package com.hshim.note.config.websocket

import com.hshim.note.model.websocket.NoteMessageRequest
import com.hshim.note.model.websocket.NoteMessageResponse
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import util.ClassUtil.jsonToClass

class CustomWebSocketHandler(private val sessionManager: SessionManager) : TextWebSocketHandler() {
    override fun afterConnectionEstablished(session: WebSocketSession) {

    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val contentMap = message.payload.jsonToClass<Map<String, Any>>()
        when (contentMap["eventName"]) {
            "JOIN_NOTE" -> {
                val content = message.payload.jsonToClass<NoteMessageRequest.JoinNoteMessage>()
                sessionManager.addSession(content.code, session)
            }

            "EDIT_NOTE" -> {
                val content = message.payload.jsonToClass<NoteMessageRequest.EditNoteMessage>()
                sessionManager.addEditSession(content.code, session)
            }

            "EDIT_CANCEL" -> {
                val content = message.payload.jsonToClass<NoteMessageRequest.EditCancelNoteMessage>()
                sessionManager.removeEditSession(content.code, session)
                if (!sessionManager.isEditing(content.code))
                    sessionManager.sendAnother(session, content.code, NoteMessageResponse.EditCancelNoteMessage())
            }

            "SAVE_NOTE" -> {
                val content = message.payload.jsonToClass<NoteMessageRequest.SaveNoteMessage>()
                sessionManager.removeEditSession(content.code, session)
                sessionManager.sendAnother(session, content.code, NoteMessageResponse.SaveNoteMessage())
            }

            else -> return
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionManager.removeSession(session)
    }
}