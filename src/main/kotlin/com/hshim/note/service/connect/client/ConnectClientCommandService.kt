package com.hshim.note.service.connect.client

import com.hshim.note.database.connectClient.ConnectClient
import com.hshim.note.database.connectClient.repository.ConnectClientRepository
import com.hshim.note.model.connect.client.ConnectClientResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ConnectClientCommandService(private val connectClientRepository: ConnectClientRepository) {
    fun buildCode(noteCodes: MutableSet<String>): ConnectClientResponse {
        return ConnectClientResponse(connectClientRepository.save(ConnectClient(noteCodes = noteCodes)))
    }

    fun addCode(connectCode: String, code: String) {
        connectClientRepository.findTopByCodeOrderByCreateDateDesc(connectCode)
            ?.apply { this.noteCodes.add(code) }
    }

    fun removeNoteCode(connectCode: String, code: String) {
        connectClientRepository.findTopByCodeOrderByCreateDateDesc(connectCode)
            ?.apply { this.noteCodes.remove(code) }
    }

    fun delete(connectCode: String) = connectClientRepository.deleteById(connectCode)
}