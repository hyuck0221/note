package com.hshim.note.database.calendar

import com.hshim.note.database.base.BaseTimeEntity
import jakarta.persistence.*
import util.CommonUtil.ulid
import java.time.LocalDateTime


@Entity
@Table(name = "calendar_date_record")
class CalendarDateRecord(
    @Id
    @Column(nullable = false)
    var id: String = ulid(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = false)
    var calendar: Calendar,

    @Column(nullable = false)
    var startDateTime: LocalDateTime,

    @Column(nullable = false)
    var endDateTime: LocalDateTime,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = true)
    var description: String?,
) : BaseTimeEntity() {

}