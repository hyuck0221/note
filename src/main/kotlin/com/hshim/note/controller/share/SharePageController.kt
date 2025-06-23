package com.hshim.note.controller.share

import com.hshim.note.model.share.enums.ShareType
import com.hshim.note.service.share.ShareQueryService
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Controller
@RequestMapping("/page/share")
class SharePageController(private val shareQueryService: ShareQueryService) {
    @GetMapping("/{code}")
    fun findByCode(@PathVariable code: String): Any {
        val body = shareQueryService.findByCode(code)
        return when (body?.type) {
            ShareType.FILE -> {
                val data = body.content as ByteArray
                val resource = ByteArrayResource(data)
                val encodedFilename = URLEncoder.encode(body.name, StandardCharsets.UTF_8.name())

                val headers = HttpHeaders().apply {
                    add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$encodedFilename")
                    add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    add(HttpHeaders.CONTENT_LENGTH, data.size.toString())
                }

                ResponseEntity(resource, headers, HttpStatus.OK)
            }

            ShareType.LINK -> {
                val link = body.content as String
                return "redirect:$link"
            }

            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}