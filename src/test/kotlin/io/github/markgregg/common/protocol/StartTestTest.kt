package io.github.markgregg.common.protocol

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.github.markgregg.common.api.Test

class StartTestTest : FunSpec({

    test("id") {
        val test = Test("id", emptyList())
        val startTest = StartTest(test)
        startTest.test shouldBe test
    }
})
