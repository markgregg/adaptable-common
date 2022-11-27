package io.github.markgregg.common.api.interfaces

import io.github.markgregg.common.api.Request
import io.github.markgregg.common.api.Response
import io.github.markgregg.common.api.Rule

class ActiveTestCase(
    val id: String,
    val rules: List<Rule>?,
    val messageHandler: (request: Request, requireResponse:Boolean) -> Response?
) {
}