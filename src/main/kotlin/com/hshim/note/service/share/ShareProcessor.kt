package com.hshim.note.service.share

import com.hshim.note.model.share.ShareBody
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ShareProcessor {
    private val body: MutableMap<String, ShareBody> = mutableMapOf()

    @Scheduled(cron = "0 * * * * *")
    fun removeExpiredBody() {
        body.filter { it.value.expireDate <= LocalDateTime.now() || it.value.remainingCnt == 0 }
            .map { it.key }
            .forEach { body.remove(it) }
    }

    fun init(shareBody: ShareBody) = shareBody.apply { body[this.code] = this }

    fun get(code: String) = body[code]?.takeIf { it.remainingCnt > 0 }?.apply { remainingCnt -= 1 }
}