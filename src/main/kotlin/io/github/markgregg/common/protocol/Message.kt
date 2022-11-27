package org.adaptable.common.protocol

import org.adaptable.common.api.Payload
import org.adaptable.common.api.Response

class Message<T : Response> (
    val endPoint: String,
    val response: T
) : Command, Payload<T> {
    override fun payload(): T? {
        return response
    }
}