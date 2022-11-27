package org.adaptable.common.protocol

import org.adaptable.common.api.Request

class EndPointRequest(
    val endPointId: String,
    val request: Request
) : Command
