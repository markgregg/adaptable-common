package org.adaptable.common.protocol

class ErrorResponse(
    error: String,
    val payload: String
) : Response(false, error) {
}