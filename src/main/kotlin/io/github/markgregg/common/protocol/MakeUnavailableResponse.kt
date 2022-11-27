package io.github.markgregg.common.protocol

class MakeUnavailableResponse(
    success: Boolean,
    message: String?
) : Response(success, message)