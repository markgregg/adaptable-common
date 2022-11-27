package org.adaptable.common.protocol

class MakeUnavailableResponse(
    success: Boolean,
    message: String?
) : Response(success, message)