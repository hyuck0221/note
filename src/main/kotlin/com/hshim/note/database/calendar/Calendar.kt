package com.hshim.note.database.calendar

import com.hshim.note.database.base.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "calendar")
class Calendar(
    @Id
    @Column(nullable = false)
    var code: String,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = true)
    var description: String?,

    @Column(nullable = false)
    var color: String,
) : BaseTimeEntity() {

    @OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY, orphanRemoval = true, cascade = [CascadeType.ALL])
    var calendarDateRecords: MutableSet<CalendarDateRecord> = mutableSetOf()

    constructor(code: String) : this(
        code = code,
        title = "제목없음",
        description = null,
        color = "#000000",
    )

    companion object {
        fun of(code: String) = Calendar(
            code = code,
            title = "",
            description = null,
            color = "#000000",
        )
    }
}