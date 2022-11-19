package org.adaptable.common.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.adaptable.common.api.utils.StubRequest
import org.mockito.Mockito.mock

class StandardRuleTest : FunSpec() {

    init {
        test("evaluate true") {
            val response = mock(Response::class.java)
            StandardRule("true", response).evaluate(StubRequest(null, "")) shouldBe response
        }

        test("evaluate true with no conditions") {
            val response = mock(Response::class.java)
            NoRule(response).evaluate(
                StubRequest(null, "")
            ) shouldBe response
        }

        test("evaluate true with multiple responses") {
            val responses = listOf(mock(Response::class.java), mock(Response::class.java))
            val rule = StandardRule("true", responses)
            rule.evaluate(StubRequest(null, "")) shouldBe responses[0]
            rule.evaluate(StubRequest(null, "")) shouldBe responses[1]
        }

        test("evaluate false") {
            val response = mock(Response::class.java)
            StandardRule("false", response).evaluate(StubRequest(null, "")).shouldBeNull()
        }

        test("response") {
            val response = mock(Response::class.java)
            StandardRule("true", response).response shouldBe response
        }

    }

}