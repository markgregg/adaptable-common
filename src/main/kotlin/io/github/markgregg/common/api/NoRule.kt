package io.github.markgregg.common.api

import io.github.markgregg.common.api.utils.ResponseManager

data class NoRule (
    val response: Response?,
    val responses: List<Response>?
) : Rule {
    private val responseManager = ResponseManager(response, responses)
    constructor(
        response: Response
    ) : this(response,null)
    constructor(
        responses: List<Response>
    ) : this(null, responses)

    override fun evaluate(request: Request): Response? = responseManager.nextResponse()

}
