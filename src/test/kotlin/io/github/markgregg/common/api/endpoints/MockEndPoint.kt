package io.github.markgregg.common.api.endpoints

import io.github.markgregg.common.api.Request
import io.github.markgregg.common.api.Response
import io.github.markgregg.common.api.Rule

class MockEndPoint(id: String, rules: List<io.github.markgregg.common.api.Rule>) :
    BaseEndPoint(id, rules, null) {
    var response: io.github.markgregg.common.api.Response? = null

    override fun handleRequest(request: io.github.markgregg.common.api.Request): io.github.markgregg.common.api.Response? {
        return response
    }

    override fun initialise() {

    }

    fun activeTestCaseId() : String? {
        return activeTestCase.get()?.id
    }

}