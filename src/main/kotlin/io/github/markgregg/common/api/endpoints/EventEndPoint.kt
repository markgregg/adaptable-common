package org.adaptable.common.api.endpoints

import org.adaptable.common.api.Response
import org.adaptable.common.api.interfaces.EndPoint
import org.adaptable.common.api.interfaces.Session

interface EventEndPoint : EndPoint {
    fun setSession(session: Session)
    fun clearSession()
    fun sendMessage(response: Response)
}