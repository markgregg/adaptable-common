package io.github.markgregg.common.api.interfaces

import io.github.markgregg.common.api.Request
import io.github.markgregg.common.api.Response
import io.github.markgregg.common.api.Rule
import io.github.markgregg.common.api.endpoints.TestCaseAlreadyActiveException
import io.github.markgregg.common.api.endpoints.TestCaseNotActiveException

interface EndPoint {
    val id: String
    val rules: List<Rule>
    val messageConverter: MessageConverter?

    fun initialise()

    @Throws(TestCaseAlreadyActiveException::class)
    fun startTest(testCase: ActiveTestCase) //test cases are queued - response won't return until previous test ends
    fun endTest(id: String)

    fun processRequest(request: Request): Response?

    @Throws(TestCaseNotActiveException::class)
    fun unavailable(id: String)
    @Throws(TestCaseNotActiveException::class)
    fun available(id: String)
}