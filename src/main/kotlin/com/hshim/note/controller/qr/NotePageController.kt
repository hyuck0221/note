package com.hshim.note.controller.qr

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/page/note")
class NotePageController {
    @GetMapping("/invite")
    fun invite() = "forward:/page/note/invite.html"
}