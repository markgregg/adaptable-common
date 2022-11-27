package org.adaptable.common.protocol

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EndTestTest : FunSpec({

    test("id") {
        val endTest = EndTest("id")
        endTest.id shouldBe "id"
    }
})
