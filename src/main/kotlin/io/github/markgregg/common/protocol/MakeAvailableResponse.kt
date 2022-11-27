package org.adaptable.common.protocol

class MakeAvailableResponse(
    success: Boolean,
    message: String?
) : Response(success, message)