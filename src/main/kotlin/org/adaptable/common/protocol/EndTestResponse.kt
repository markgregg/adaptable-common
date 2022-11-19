package org.adaptable.common.protocol

class EndTestResponse(
    success: Boolean,
    message: String?
) : Response(success, message)