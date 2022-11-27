package org.adaptable.common.api.interfaces

import org.adaptable.common.api.Request
import org.adaptable.common.api.Response
import org.adaptable.common.api.Rule

class ActiveTestCase(
    val id: String,
    val rules: List<Rule>?,
    val messageHandler: (request: Request,requireResponse:Boolean) -> Response?
) {
}