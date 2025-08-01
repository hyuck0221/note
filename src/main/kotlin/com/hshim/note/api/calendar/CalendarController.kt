package com.hshim.note.api.calendar

import com.hshim.note.model.calendar.CalendarDateRecordRequest
import com.hshim.note.model.calendar.CalendarDateRecordResponse
import com.hshim.note.model.calendar.CalendarResponse
import com.hshim.note.service.calendar.CalendarCommandService
import com.hshim.note.service.calendar.CalendarQueryService
import org.springframework.web.bind.annotation.*
import java.time.YearMonth

@RestController
@RequestMapping("/calendar")
class CalendarController(
    private val calendarQueryService: CalendarQueryService,
    private val calendarCommandService: CalendarCommandService,
) {
    @PostMapping
    fun init(): CalendarResponse {
        return calendarCommandService.init(null)
    }

    @PostMapping("/{code}")
    fun init(@PathVariable code: String): CalendarResponse {
        return calendarCommandService.init(code)
    }

    @PutMapping("/{code}/title")
    fun updateTitle(
        @PathVariable code: String,
        @RequestBody request: Map<String, String?>,
    ): CalendarResponse {
        return calendarCommandService.updateTitle(code, request["title"] ?: "제목없음")
    }

    @PutMapping("/{code}/description")
    fun updateDescription(
        @PathVariable code: String,
        @RequestBody request: Map<String, String?>,
    ): CalendarResponse {
        return calendarCommandService.updateDescription(code, request["description"])
    }

    @GetMapping("/list")
    fun findAllByCodes(@RequestParam codes: List<String>): List<CalendarResponse> {
        return calendarQueryService.findAllByCodes(codes)
    }

    @GetMapping("/search")
    fun findAllDateRecordsByDateYearMonthBetween(
        @RequestParam codes: List<String>,
        @RequestParam startYearMonth: YearMonth,
        @RequestParam endYearMonth: YearMonth,
    ): List<CalendarDateRecordResponse> {
        return calendarQueryService.findAllByDateYearMonthBetween(codes, startYearMonth, endYearMonth)
    }

    @PostMapping("/{code}/record")
    fun initRecord(
        @PathVariable code: String,
        @RequestBody request: CalendarDateRecordRequest,
    ): CalendarDateRecordResponse {
        return calendarCommandService.initRecord(code, request)
    }

    @PostMapping("/{code}/record/{id}")
    fun updateRecord(
        @PathVariable code: String,
        @PathVariable id: String,
        @RequestBody request: CalendarDateRecordRequest,
    ): CalendarDateRecordResponse {
        return calendarCommandService.updateRecord(code, id, request)
    }

    @DeleteMapping("/{code}/record/{id}")
    fun deleteRecord(
        @PathVariable code: String,
        @PathVariable id: String,
    ) {
        return calendarCommandService.deleteRecord(code, id)
    }
}