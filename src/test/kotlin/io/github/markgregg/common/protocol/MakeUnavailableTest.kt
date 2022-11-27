package io.github.markgregg.common.protocol

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MakeUnavailableTest : FunSpec({

    test("id") {
        val makeUnavailable = MakeUnavailable("id")
        makeUnavailable.endPoint shouldBe "id"
    }
})
