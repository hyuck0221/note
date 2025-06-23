package com.hshim.note.service.share

import com.hshim.note.model.share.ShareBody
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ShareCommandService(private val shareProcessor: ShareProcessor) {
    fun init(file: MultipartFile, remainingCnt: Int?) = shareProcessor.init(ShareBody.ofFile(file, remainingCnt))
    fun init(link: String, remainingCnt: Int?) = shareProcessor.init(ShareBody.ofLink(link, remainingCnt))
}