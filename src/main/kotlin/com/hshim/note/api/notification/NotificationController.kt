package com.hshim.note.api.notification

import com.hshim.note.model.notification.NotificationRequest
import com.hshim.note.model.notification.NotificationResponse
import com.hshim.note.service.notification.NotificationCommandService
import com.hshim.note.service.notification.NotificationQueryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notification")
class NotificationController(
    private val notificationQueryService: NotificationQueryService,
    private val notificationCommandService: NotificationCommandService,
) {
    @PostMapping
    fun init(@RequestBody request: NotificationRequest): NotificationResponse {
        return notificationCommandService.init(request)
    }

    @PutMapping("/{number}")
    fun update(@PathVariable number: Long, @RequestBody request: NotificationRequest): NotificationResponse? {
        return notificationCommandService.update(number, request)
    }

    @GetMapping("/{number}")
    fun findBy(@PathVariable number: Long): NotificationResponse? {
        return notificationQueryService.findBy(number)
    }

    @GetMapping
    fun findTopBy(): NotificationResponse? {
        return notificationQueryService.findTopBy()
    }

    @GetMapping("/terms-of-used")
    fun findTermsOfUsed(): NotificationResponse? {
        return notificationQueryService.findTermsOfUsed()
    }
}