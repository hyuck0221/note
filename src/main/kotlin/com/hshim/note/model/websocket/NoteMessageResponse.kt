package com.hshim.note.model.websocket

class NoteMessageResponse {
    class UserCntNoteMessage(val userCnt: Int) : BaseMessageModel("USER_CNT_NOTE")
    class EditNoteMessage() : BaseMessageModel("EDIT_NOTE")
    class EditCancelNoteMessage() : BaseMessageModel("EDIT_CANCEL_NOTE")
    class SaveNoteMessage() : BaseMessageModel("SAVE_NOTE")
}