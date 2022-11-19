package org.adaptable.common.protocol

import org.adaptable.common.api.Request


class RequestResponse(
    val endPointId: String,
    val request: Request
) : Response(true, "")