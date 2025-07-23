package com.hshim.note.model.websocket

class NoteMessageRequest {
    class JoinNoteMessage(val code: String): BaseMessageModel("JOIN_NOTE")
    class EditNoteMessage(val code: String): BaseMessageModel("EDIT_NOTE")
    class EditCancelNoteMessage(val code: String): BaseMessageModel("EDIT_CANCEL_NOTE")
    class SaveNoteMessage(val code: String): BaseMessageModel("SAVE_NOTE")
}