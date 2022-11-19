package org.adaptable.common.api.interfaces

import org.adaptable.common.api.Response

interface Session {
    val sessionId: String
    fun sendResponse(response: Response)
}