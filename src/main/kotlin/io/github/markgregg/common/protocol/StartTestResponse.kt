package io.github.markgregg.common.protocol

class StartTestResponse(
    success: Boolean,
    message: String?
) : Response(success, message)