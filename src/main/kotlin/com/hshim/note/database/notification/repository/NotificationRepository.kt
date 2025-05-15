package com.hshim.note.database.notification.repository

import com.hshim.note.database.notification.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findTopByOrderByCreateDateAsc(): Notification?
    fun findTopByOrderByCreateDateDesc(): Notification?
}