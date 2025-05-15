package com.hshim.note.database.notification

import com.hshim.note.database.base.BaseTimeEntity
import jakarta.persistence.*


@Entity
@Table(name = "notification")
class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var number: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,
) : BaseTimeEntity()