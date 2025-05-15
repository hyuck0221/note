package com.hshim.note.controller.admin.notification

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/page/admin/notification")
class AdminNotificationPageController {
    @GetMapping("/init")
    fun invite() = "forward:/page/admin/notification/init.html"
}