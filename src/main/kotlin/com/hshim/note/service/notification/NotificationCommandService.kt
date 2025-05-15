package com.hshim.note.service.notification

import com.hshim.note.database.notification.repository.NotificationRepository
import com.hshim.note.model.notification.NotificationRequest
import com.hshim.note.model.notification.NotificationResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class NotificationCommandService(private val notificationRepository: NotificationRepository) {
    fun init(request: NotificationRequest): NotificationResponse {
        return NotificationResponse(notificationRepository.save(request.toEntity()))
    }

    fun update(number: Long, request: NotificationRequest): NotificationResponse? {
        val notification = notificationRepository.findByIdOrNull(number)
            ?.apply { request.updateTo(this) }
        return notification?.let { NotificationResponse(it) }
    }
}