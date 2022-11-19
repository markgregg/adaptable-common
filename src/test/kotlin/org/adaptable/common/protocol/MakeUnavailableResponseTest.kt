package org.adaptable.common.protocol

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MakeUnavailableResponseTest : FunSpec({
    test("test response") {
        val endTestResponse = EndTestResponse(true, "test")
        endTestResponse.success shouldBe true
        endTestResponse.message shouldBe "test"
    }

})
