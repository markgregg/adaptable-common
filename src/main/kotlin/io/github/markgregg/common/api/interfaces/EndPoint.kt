package org.adaptable.common.api.interfaces

import org.adaptable.common.api.Request
import org.adaptable.common.api.Response
import org.adaptable.common.api.Rule
import org.adaptable.common.api.endpoints.TestCaseAlreadyActiveException
import org.adaptable.common.api.endpoints.TestCaseNotActiveException

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