package com.hshim.note.api

import com.hshim.note.model.note.NoteResponse
import com.hshim.note.service.note.NoteCommandService
import com.hshim.note.service.note.NoteQueryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/note")
class NoteController(
    private val noteQueryService: NoteQueryService,
    private val noteCommandService: NoteCommandService,
) {
    @PostMapping
    fun init(@RequestHeader("Connect-Code") connectCode: String?): NoteResponse {
        return noteCommandService.init(null, connectCode)
    }

    @PostMapping("/{code}")
    fun init(
        @RequestHeader("Connect-Code") connectCode: String?,
        @PathVariable code: String,
    ): NoteResponse {
        return noteCommandService.init(code, connectCode)
    }

    @PutMapping("/{code}/title")
    fun updateTitle(
        @PathVariable code: String,
        @RequestBody request: Map<String, String?>,
    ): NoteResponse {
        return noteCommandService.updateTitle(code, request["title"] ?: "제목없음")
    }

    @PutMapping("/{code}/content")
    fun updateContent(
        @PathVariable code: String,
        @RequestBody request: Map<String, String?>,
    ): NoteResponse {
        return noteCommandService.updateContent(code, request["content"])
    }

    @GetMapping("/list")
    fun findAllBy(@RequestParam codes: List<String>): List<NoteResponse> {
        return noteQueryService.findAllBy(codes)
    }
}