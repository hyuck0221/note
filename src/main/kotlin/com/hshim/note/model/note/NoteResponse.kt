package com.hshim.note.model.note

import com.hshim.note.database.note.Note

class NoteResponse(
    val code: String,
    val title: String,
    val content: String,
) {
    constructor(note: Note) : this(
        code = note.code,
        title = note.title,
        content = note.content,
    )
}