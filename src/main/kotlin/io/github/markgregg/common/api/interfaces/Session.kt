package io.github.markgregg.common.api.interfaces

import io.github.markgregg.common.api.Response

interface Session {
    val sessionId: String
    fun sendResponse(response: Response)
}