package io.github.markgregg.common.api.endpoints

import io.github.markgregg.common.api.Response
import io.github.markgregg.common.api.interfaces.EndPoint
import io.github.markgregg.common.api.interfaces.Session

interface EventEndPoint : EndPoint {
    fun setSession(session: Session)
    fun clearSession()
    fun sendMessage(response: Response)
}