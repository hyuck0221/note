package com.hshim.note.controller.calendar

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/page/calendar")
class CalendarPageController {
    @GetMapping
    fun main() = "forward:/page/calendar/main.html"
}