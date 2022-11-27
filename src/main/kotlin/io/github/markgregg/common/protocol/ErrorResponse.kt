package io.github.markgregg.common.protocol

class ErrorResponse(
    error: String,
    val payload: String
) : Response(false, error) {
}