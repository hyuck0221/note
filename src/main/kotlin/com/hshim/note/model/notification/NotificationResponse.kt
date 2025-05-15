package com.hshim.note.model.notification

import com.hshim.note.database.notification.Notification

class NotificationResponse(
    val number: Long,
    val title: String,
    val content: String,
) {
    constructor(notification: Notification) : this(
        number = notification.number!!,
        title = notification.title,
        content = notification.content,
    )
}