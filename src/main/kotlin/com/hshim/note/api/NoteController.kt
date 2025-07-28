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
    fun init(): NoteResponse {
        return noteCommandService.init(null)
    }

    @PostMapping("/{code}")
    fun init(@PathVariable code: String): NoteResponse {
        return noteCommandService.init(code)
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

    @PutMapping("/{code}/description")
    fun updateDescription(
        @PathVariable code: String,
        @RequestBody request: Map<String, String?>,
    ): NoteResponse {
        return noteCommandService.updateDescription(code, request["description"])
    }

    @GetMapping("/list")
    fun findAllBy(@RequestParam codes: List<String>): List<NoteResponse> {
        return noteQueryService.findAllBy(codes)
    }
}