package com.hshim.note.service.calendar

import com.hshim.note.database.calendar.Calendar
import com.hshim.note.database.calendar.repository.CalendarDateRecordRepository
import com.hshim.note.database.calendar.repository.CalendarRepository
import com.hshim.note.model.calendar.CalendarDateRecordRequest
import com.hshim.note.model.calendar.CalendarDateRecordResponse
import com.hshim.note.model.calendar.CalendarResponse
import com.hshim.note.util.CodeUtil.generateRandomCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CalendarCommandService(
    private val calendarRepository: CalendarRepository,
    private val calendarDateRecordRepository: CalendarDateRecordRepository,
) {
    fun init(code: String?): CalendarResponse {
        var calendar = code?.let { calendarRepository.findByIdOrNull(it) }
        if (calendar == null) {
            var code = code ?: generateRandomCode()
            while (calendarRepository.existsById(code)) {
                code = generateRandomCode()
            }
            calendar = calendarRepository.save(Calendar(code))
        }
        return CalendarResponse(calendar)
    }

    fun updateTitle(code: String, title: String): CalendarResponse {
        return calendarRepository.findByIdOrNull(code)
            ?.apply { this.title = title }
            ?.let { CalendarResponse(it) }
            ?: throw Exception("calendar not found.")
    }

    fun updateDescription(code: String, description: String?): CalendarResponse {
        return calendarRepository.findByIdOrNull(code)
            ?.apply { this.description = description ?: "" }
            ?.let { CalendarResponse(it) }
            ?: throw Exception("calendar not found.")
    }

    fun initRecord(code: String, request: CalendarDateRecordRequest): CalendarDateRecordResponse {
        val entity = request.toEntity(code)
        return calendarRepository.findByIdOrNull(code)
            ?.apply { this.calendarDateRecords.add(request.toEntity(code)) }
            ?.let { CalendarDateRecordResponse(entity) }
            ?: throw Exception("calendar not found.")
    }

    fun updateRecord(code: String, id: String, request: CalendarDateRecordRequest): CalendarDateRecordResponse {

        if (!calendarRepository.existsById(code)) throw Exception("calendar not found.")

        return calendarDateRecordRepository.findByIdOrNull(id)
            ?.apply { request.updateTo(this) }
            ?.let { CalendarDateRecordResponse(it) }
            ?: throw Exception("calendar record not found.")
    }

    fun deleteRecord(code: String, id: String) {
        if (!calendarRepository.existsById(code)) throw Exception("calendar not found.")
        calendarDateRecordRepository.deleteById(id)
    }
}