package io.github.markgregg.common.protocol

import io.github.markgregg.common.api.Response

class EndpointResponse(
    val endPoint: String,
    val response: io.github.markgregg.common.api.Response
) : Command