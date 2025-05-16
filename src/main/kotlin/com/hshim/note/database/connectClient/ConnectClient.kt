package com.hshim.note.database.connectClient

import com.hshim.note.database.base.BaseTimeEntity
import com.hshim.note.database.converter.StringSetConverter
import com.hshim.note.util.CodeUtil.generateRandomCode
import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "connect_client")
class ConnectClient(
    @Id
    @Column(nullable = false)
    val id: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    val code: String = generateRandomCode(),

    @Column(nullable = false, columnDefinition = "varchar(255)")
    @Convert(converter = StringSetConverter::class)
    val noteCodes: MutableSet<String>,
) : BaseTimeEntity()