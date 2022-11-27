package org.adaptable.common.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class EndPointTest : FunSpec({

    test("id") {
        val properties = emptyMap<String,String>()
        val rules = listOf<Rule>()
        EndPoint("test", "type", properties, rules, "converter", false).id shouldBe "test"
    }

    test("type") {
        val properties = emptyMap<String,String>()
        val rules = listOf<Rule>()
        EndPoint("test", "type", properties, rules, "converter", false).type shouldBe "type"
    }

    test("properties") {
        val properties = emptyMap<String,String>()
        val rules = listOf<Rule>()
        EndPoint("test", "type", properties, rules, "converter", false).properties shouldBe properties
    }

    test("rules") {
        val properties = emptyMap<String,String>()
        val rules = listOf<Rule>()
        EndPoint("test", "type", properties, rules, "converter", false).rules shouldBe rules
    }

    test("messageConverter") {
        val properties = emptyMap<String,String>()
        val rules = listOf<Rule>()
        EndPoint("test", "type", properties, rules, "converter", false).messageConverter shouldBe "converter"
    }
})
