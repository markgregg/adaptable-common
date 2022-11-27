package io.github.markgregg.common.protocol

class MakeAvailableResponse(
    success: Boolean,
    message: String?
) : Response(success, message)