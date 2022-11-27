package org.adaptable.common.api.endpoints

import org.adaptable.common.api.Request
import org.adaptable.common.api.Response
import org.adaptable.common.api.Rule

class MockEndPoint(id: String, rules: List<Rule>) :
    BaseEndPoint(id, rules, null) {
    var response: Response? = null

    override fun handleRequest(request: Request): Response? {
        return response
    }

    override fun initialise() {

    }

    fun activeTestCaseId() : String? {
        return activeTestCase.get()?.id
    }

}