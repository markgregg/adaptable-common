package io.github.markgregg.common.protocol

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MakeAvailableTest : FunSpec({

    test("id") {
        val makeAvailable = MakeAvailable("id")
        makeAvailable.endPoint shouldBe "id"
    }
})
