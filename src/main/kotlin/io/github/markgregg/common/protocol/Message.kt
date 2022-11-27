package io.github.markgregg.common.protocol

import io.github.markgregg.common.api.Payload
import io.github.markgregg.common.api.Response

class Message<T : io.github.markgregg.common.api.Response> (
    val endPoint: String,
    val response: T
) : Command, io.github.markgregg.common.api.Payload<T> {
    override fun payload(): T? {
        return response
    }
}