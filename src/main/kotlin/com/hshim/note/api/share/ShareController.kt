package com.hshim.note.api.share

import com.hshim.note.model.share.ShareLinkRequest
import com.hshim.note.service.share.ShareCommandService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/share")
class ShareController(private val shareCommandService: ShareCommandService) {
    @PostMapping("/file", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun initFile(
        @RequestPart("file") file: MultipartFile,
        @RequestParam("remainingCnt", required = false) remainingCnt: Int?
    ) = shareCommandService.init(file, remainingCnt)

    @PostMapping("/link")
    fun initLink(
        @RequestBody request: ShareLinkRequest,
    ) = shareCommandService.init(request.link, request.remainingCnt)
}