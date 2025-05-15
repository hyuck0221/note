package com.hshim.note.service.notification

import com.hshim.note.database.notification.repository.NotificationRepository
import com.hshim.note.model.notification.NotificationResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NotificationQueryService(private val notificationRepository: NotificationRepository) {
    fun findBy(number: Long): NotificationResponse? {
        return notificationRepository.findByIdOrNull(number)
            ?.let { NotificationResponse(it) }
    }

    fun findTopBy(): NotificationResponse? {
        return notificationRepository.findTopByOrderByCreateDateDesc()
            ?.let { NotificationResponse(it) }
    }

    fun findTermsOfUsed(): NotificationResponse? {
        return notificationRepository.findTopByOrderByCreateDateAsc()
            ?.let { NotificationResponse(it) }
    }
}