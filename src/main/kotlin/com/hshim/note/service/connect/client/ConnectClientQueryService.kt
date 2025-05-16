package com.hshim.note.service.connect.client

import com.hshim.note.database.connectClient.repository.ConnectClientRepository
import com.hshim.note.model.connect.client.ConnectClientResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ConnectClientQueryService(private val connectClientRepository: ConnectClientRepository) {
    fun findByCode(code: String): ConnectClientResponse? {
        return connectClientRepository.findTopByCodeOrderByCreateDateDesc(code)
            ?.let { return ConnectClientResponse(it) }
    }
}