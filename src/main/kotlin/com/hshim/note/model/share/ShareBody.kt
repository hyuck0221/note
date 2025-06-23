package com.hshim.note.model.share

import com.fasterxml.jackson.annotation.JsonIgnore
import com.hshim.note.model.share.enums.ShareType
import com.hshim.note.util.CodeUtil
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

class ShareBody(
    val type: ShareType,
    @JsonIgnore
    val content: Any,
    var remainingCnt: Int,
) {
    val code = CodeUtil.generateRandomCode()
    val createDate: LocalDateTime = LocalDateTime.now()
    val expireDate: LocalDateTime = createDate.plusHours(1)
    var name: String? = null

    companion object {
        fun ofFile(file: MultipartFile, remainingCnt: Int? = 1) = ShareBody(
            type = ShareType.FILE,
            content = file.bytes,
            remainingCnt = remainingCnt ?: 1
        ).apply { name = file.originalFilename }

        fun ofLink(link: String, remainingCnt: Int? = 1) = ShareBody(
            type = ShareType.LINK,
            content = link,
            remainingCnt = remainingCnt ?: 1,
        )
    }
}