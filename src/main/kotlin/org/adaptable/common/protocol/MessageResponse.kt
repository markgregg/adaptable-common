package org.adaptable.common.protocol

class MessageResponse(
    success: Boolean,
    message: String?
) : Response(success, message)