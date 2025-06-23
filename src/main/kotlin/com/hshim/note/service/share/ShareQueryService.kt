package com.hshim.note.service.share

import com.hshim.note.model.share.ShareBody
import org.springframework.stereotype.Service

@Service
class ShareQueryService(private val shareProcessor: ShareProcessor) {
    fun findByCode(code: String): ShareBody? {
        return shareProcessor.get(code)
    }
}