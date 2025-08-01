package com.hshim.note.database.calendar.repository

import com.hshim.note.database.calendar.CalendarDateRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface CalendarDateRecordRepository : JpaRepository<CalendarDateRecord, String> {

    @Query(
        """
            select cdr from CalendarDateRecord cdr 
            inner join Calendar c on c = cdr.calendar 
            where c.code in :codes 
            and (
                cdr.startDateTime between :startDateTime and :endDateTime
                or cdr.endDateTime between :startDateTime and :endDateTime
            ) 
        """
    )
    fun findAllByCalendarCodeInAndDateBetween(
        codes: List<String>,
        startDateTime: LocalDateTime,
        endDateTime: LocalDateTime,
    ): List<CalendarDateRecord>
}