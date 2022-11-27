package io.github.markgregg.common.protocol

import io.github.markgregg.common.api.Request

class EndPointRequest(
    val endPointId: String,
    val request: io.github.markgregg.common.api.Request
) : Command
