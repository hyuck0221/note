package com.hshim.note.model.connect.client

import com.hshim.note.database.connectClient.ConnectClient

class ConnectClientResponse(
    val id: String,
    val code: String,
    val noteCodes: Set<String>,
) {
    constructor(connectClient: ConnectClient) : this(
        id = connectClient.id,
        code = connectClient.code,
        noteCodes = connectClient.noteCodes,
    )
}