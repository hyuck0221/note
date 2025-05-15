package com.hshim.note.model.notification

import com.hshim.note.database.notification.Notification

class NotificationRequest(
    val title: String,
    val content: String,
) {
    fun toEntity() = Notification(
        title = title,
        content = content,
    )

    fun updateTo(notification: Notification) {
        notification.title = title
        notification.content = content
    }
}