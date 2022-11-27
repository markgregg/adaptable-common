package io.github.markgregg.common.api

import io.github.markgregg.common.api.utils.ResponseManager
import io.github.markgregg.expression.Evaluator
import io.github.markgregg.expression.operations.Operation

data class StandardRule (
    val expression: String?,
    val response: Response?,
    val responses: List<Response>?,
    val otherwise: Rule?
) : Rule {
    private val responseManager = ResponseManager(response, responses)
    constructor(
        expression: String,
        response: Response,
        otherwise: Rule
    ) : this(expression,response,null, otherwise)
    constructor(
        expression: String,
        response: Response
    ) : this(expression,response,null, null)
    constructor(
        expression: String,
        responses: List<Response>,
        otherwise: Rule
    ) : this(expression, null, responses, otherwise)
    constructor(
        expression: String,
        responses: List<Response>
    ) : this(expression, null, responses, null)
    private var operation: Operation? = null

    override fun evaluate(request: Request): Response? {
        return if( operation == null && expression == null ||
            evaluateRequest(request)
        ) {
            responseManager.nextResponse()
        } else {
            otherwise?.evaluate( request )
        }
    }

    private fun evaluateRequest(request: Request): Boolean {
        if( operation == null ) {
            operation = Evaluator.instance().compile(expression!!)
        }
        return Evaluator.instance().execute(operation!!,request) as Boolean
    }
}