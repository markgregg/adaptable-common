package org.adaptable.common.protocol

class StartTestResponse(
    success: Boolean,
    message: String?
) : Response(success, message)