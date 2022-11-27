package io.github.markgregg.common.protocol

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MakeAvailableResponseTest : FunSpec({
    test("test response") {
        val endTestResponse = EndTestResponse(true, "test")
        endTestResponse.success shouldBe true
        endTestResponse.message shouldBe "test"
    }

})
