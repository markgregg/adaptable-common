package io.github.markgregg.common.protocol

class MessageResponse(
    success: Boolean,
    message: String?
) : Response(success, message)