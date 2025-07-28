package com.hshim.note.database.connectClient

import com.hshim.note.database.base.BaseTimeEntity
import com.hshim.note.util.CodeUtil.generateRandomCode
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import util.CommonUtil.ulid


@Entity
@Table(name = "connect_client")
class ConnectClient(
    @Id
    @Column(nullable = false)
    val id: String = ulid(),

    @Column(nullable = false)
    val code: String = generateRandomCode(),

    @Column(nullable = false, columnDefinition = "TEXT")
    @ColumnDefault("''")
    var localStorageJson: String,
) : BaseTimeEntity()