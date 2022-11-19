package org.adaptable.common.protocol

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.adaptable.common.api.Test

class StartTestTest : FunSpec({

    test("id") {
        val test = Test("id", emptyList())
        val startTest = StartTest(test)
        startTest.test shouldBe test
    }
})
