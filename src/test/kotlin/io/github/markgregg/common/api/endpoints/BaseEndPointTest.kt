package org.adaptable.common.api.endpoints

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.adaptable.common.api.Response
import org.adaptable.common.api.Rule
import org.adaptable.common.api.StandardRule
import org.adaptable.common.api.interfaces.ActiveTestCase
import org.adaptable.common.api.utils.StubRequest
import org.mockito.Mockito.mock
import java.util.concurrent.atomic.AtomicBoolean

class BaseEndPointTest : FunSpec({

	test("id") { }

	test("startTest") {
		val endPoint = createMock( )
		endPoint.startTest(ActiveTestCase("id", emptyList()) { _,_ -> null })
		endPoint.activeTestCaseId() shouldBe "id"
	}

	test("endTest") {
		val endPoint = createMock( )
		endPoint.startTest(ActiveTestCase("id", emptyList()) { _,_ -> null })
		endPoint.activeTestCaseId() shouldBe "id"
		endPoint.endTest("id")
		endPoint.activeTestCaseId().shouldBeNull()
	}


	test("service is unavailable when id matches current test case") {
		val endPoint = createMock( )
		endPoint.startTest(ActiveTestCase("id", emptyList()) { _,_ -> null })
		endPoint.unavailable("id" )
		endPoint.unavailable.get() shouldBe true
	}

	test("unavailableStatus does not match test case") {
		val endPoint = createMock( )
		endPoint.startTest(ActiveTestCase("id", emptyList()) { _,_ -> null })
		shouldThrowExactly<TestCaseNotActiveException> {
			endPoint.unavailable("id2")
		}
	}

	test("available Status does not match test case") {
		val endPoint = createMock( )
		endPoint.startTest(ActiveTestCase("id", emptyList()) { _,_ -> null })
		shouldThrowExactly<TestCaseNotActiveException> {
			endPoint.available("id2")
		}
	}

	test("service is still available when make available") {
		val endPoint = createMock( )
		endPoint.startTest(ActiveTestCase("id", emptyList()) { _,_ -> null })
		endPoint.unavailable("id")
		endPoint.available("id")
        endPoint.unavailable.get() shouldBe false
	}

	test("processRequest no matches return null") {
		val endPoint = createMock( )
		val isCalled = AtomicBoolean()

		endPoint.startTest(ActiveTestCase("id", emptyList()) { _,_ -> 
			isCalled.set(true)
			null }
		)
		endPoint.processRequest(
			StubRequest(
			emptyMap(),
			"{\"field\":\"value2\"}"
		)
		).shouldBeNull()
		isCalled.get() shouldBe true
	}

	test("rule matches should return a response object") {
		val response = mock(Response::class.java)
		val endPoint = createMock(rules = listOf(
			StandardRule(
				"\$body.field=='value'",
				response
			)
		) )
		endPoint.processRequest(StubRequest(
			emptyMap(),
			"{\"field\":\"value\"}"
		)) shouldBe response
	}

	test("rule custom responses should proceed rules") {
		val response = mock(Response::class.java)
		val endPoint = createMock(rules = listOf(
			StandardRule(
				"\$body.field=='value'",
				response
			)
		) )
		endPoint.response = mock(Response::class.java)
		endPoint.processRequest(StubRequest(
			emptyMap(),
			"{\"field\":\"value\"}"
		)) shouldBe endPoint.response
	}

	test("supplied test case should proceed test cases and custom rules") {
		val response = mock(Response::class.java)
		val endPoint = createMock(
			rules = listOf(
				StandardRule(
					"\$body.field=='value'",
					response
				)
			)
		)
		val response3 = mock(Response::class.java)
		endPoint.startTest(
			ActiveTestCase(
				"id",
				listOf(StandardRule("\$body.field=='value'", response3))
			) { _,_ -> null }
		)

		endPoint.response = mock(Response::class.java)
		endPoint.processRequest(StubRequest(
			emptyMap(),
			"{\"field\":\"value\"}"
		)) shouldBe response3
	}

}) {
  companion object {
   private fun createMock(rules: List<Rule> = emptyList(), ): MockEndPoint {
 		return MockEndPoint("test", rules)
   }
  }
}
