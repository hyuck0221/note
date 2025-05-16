package com.hshim.note.api.connect.client

import com.hshim.note.model.connect.client.ConnectClientResponse
import com.hshim.note.model.note.NoteResponse
import com.hshim.note.service.connect.client.ConnectClientCommandService
import com.hshim.note.service.connect.client.ConnectClientQueryService
import com.hshim.note.service.note.NoteCommandService
import com.hshim.note.service.note.NoteQueryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/connect/client")
class ConnectClientController(
    private val connectClientQueryService: ConnectClientQueryService,
    private val connectClientCommandService: ConnectClientCommandService,
) {
    @PostMapping
    fun init(@RequestParam noteCodes: List<String>): ConnectClientResponse {
        return connectClientCommandService.buildCode(noteCodes.toMutableSet())
    }

    @DeleteMapping("/note/codes/{code}")
    fun removeNoteCode(
        @RequestHeader("Connect-Code") connectCode: String,
        @PathVariable code: String
    ) = connectClientCommandService.removeNoteCode(connectCode, code)

    @DeleteMapping
    fun delete(
        @RequestHeader("Connect-Code") connectCode: String,
    ) = connectClientCommandService.delete(connectCode)

    @GetMapping
    fun findByCode(@RequestHeader("Connect-Code") connectCode: String): ConnectClientResponse? {
        return connectClientQueryService.findByCode(connectCode)
    }
}