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
    @PostMapping("/{code}")
    fun init(@PathVariable code: String): NoteResponse {
        return noteCommandService.init(code)
    }

    @PutMapping("/{code}/title")
    fun updateTitle(
        @PathVariable code: String,
        @RequestBody title: String,
    ) = noteCommandService.updateTitle(code, title)

    @PutMapping("/{code}/content")
    fun updateContent(
        @PathVariable code: String,
        @RequestBody content: String?,
    ) = noteCommandService.updateContent(code, content)

    @GetMapping("/list")
    fun findAllBy(@RequestParam codes: List<String>): List<NoteResponse> {
        return noteQueryService.findAllBy(codes)
    }
}