package com.hshim.note.model.note

import com.hshim.note.database.note.Note
import io.autocrypt.sakarinblue.universe.util.DateUtil.dateToString

class NoteResponse(
    val code: String,
    val title: String,
    val description: String?,
    val content: String,
    val createDate: String,
    val updateDate: String,
) {
    constructor(note: Note) : this(
        code = note.code,
        title = note.title,
        description = note.description,
        content = note.content,
        createDate = note.createDate.dateToString(),
        updateDate = note.updateDate.dateToString(),
    )
}