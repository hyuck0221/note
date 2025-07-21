package com.hshim.note.database.note

import com.hshim.note.database.base.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*


@Entity
@Table(name = "note")
class Note(
    @Id
    @Column(nullable = false)
    var code: String,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = true)
    var description: String?,

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    var content: String,
) : BaseTimeEntity() {
    constructor(code: String) : this(
        code = code,
        title = "제목없음",
        description = null,
        content = "",
    )
}