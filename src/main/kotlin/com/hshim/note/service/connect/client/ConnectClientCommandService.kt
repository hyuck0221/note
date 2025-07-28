package com.hshim.note.service.connect.client

import com.hshim.note.database.connectClient.ConnectClient
import com.hshim.note.database.connectClient.repository.ConnectClientRepository
import com.hshim.note.model.connect.client.ConnectClientResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
@Transactional
class ConnectClientCommandService(private val connectClientRepository: ConnectClientRepository) {
    fun build(localStorageJson: String): ConnectClientResponse {
        return ConnectClientResponse(connectClientRepository.save(ConnectClient(localStorageJson = localStorageJson)))
    }

    fun update(connectCode: String, localStorageJson: String): ConnectClientResponse {
        return connectClientRepository.findTopByCodeOrderByCreateDateDesc(connectCode)
            ?.apply { this.localStorageJson = localStorageJson }
            ?.let { ConnectClientResponse(it) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun delete(connectCode: String) = connectClientRepository.deleteById(connectCode)
}