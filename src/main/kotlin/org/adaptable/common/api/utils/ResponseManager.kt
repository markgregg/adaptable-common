package org.adaptable.common.api.utils

import org.adaptable.common.api.Response
import java.util.concurrent.atomic.AtomicInteger

class ResponseManager(
    val response: Response?,
    val responses: List<Response>?
) {
    private val responseIterator = AtomicInteger()

    fun nextResponse(): Response? {
        return if( responses == null) {
            response
        } else {
            if( responseIterator.get() >= responses.size ) {
                responseIterator.set(0)
            }
            responses[responseIterator.getAndIncrement()]
        }
    }
}