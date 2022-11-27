package io.github.markgregg.common.protocol

class EndTestResponse(
    success: Boolean,
    message: String?
) : Response(success, message)