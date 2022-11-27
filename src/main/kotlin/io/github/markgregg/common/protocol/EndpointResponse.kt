package org.adaptable.common.protocol

import org.adaptable.common.api.Response

class EndpointResponse(
    val endPoint: String,
    val response: Response
) : Command