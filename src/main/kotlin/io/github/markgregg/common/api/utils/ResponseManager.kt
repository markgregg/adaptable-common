package io.github.markgregg.common.api.utils

import io.github.markgregg.common.api.Response
import java.util.concurrent.atomic.AtomicInteger

class ResponseManager(
    val response: Response?,
    val responses: List<Response>?
) {
    private val responseIterator = AtomicInteger()

    fun nextResponse(): Response? = if( responses == null) {
            response
        } else {
            if( responseIterator.get() >= responses.size ) {
                responseIterator.set(0)
            }
            responses[responseIterator.getAndIncrement()]
        }
}