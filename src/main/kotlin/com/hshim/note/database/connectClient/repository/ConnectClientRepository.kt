package com.hshim.note.database.connectClient.repository

import com.hshim.note.database.connectClient.ConnectClient
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ConnectClientRepository : JpaRepository<ConnectClient, String> {
    fun findTopByCodeOrderByCreateDateDesc(code: String): ConnectClient?
}