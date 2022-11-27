package org.adaptable.common.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.nio.charset.StandardCharsets

class ServiceDefinitionTest : FunSpec({

    test("extensionDir") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .extensionDir shouldBe "extensionDir"
    }
    
    test("keyStoreType") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .keyStoreType shouldBe "type"
    }

    test("keyStorePath") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .keyStorePath shouldBe "path"
    }

    test("keySorePassword") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .keySorePassword shouldBe "password"
    }

    test("keyAlias") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .keyAlias shouldBe "alias"
    }

    test("ports") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .ports shouldBe ports
    }

    test("securePorts") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .securePorts shouldBe securePorts
    }

    test("testCaseTimeout") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .testCaseTimeout shouldBe 1000
    }

    test("endPoints") {
        val ports = listOf<Int>()
        val securePorts = listOf<Int>()
        val endPoints = listOf<EndPoint>()
        ServiceDefinition("extensionDir", "type", "path", "password", "alias", ports, securePorts, 1000, endPoints)
            .endPoints shouldBe endPoints
    }

    test( "load from json") {
        val text = String(xml.byteInputStream().readAllBytes(), StandardCharsets.UTF_8)
        val result = jacksonObjectMapper().readValue(text, ServiceDefinition::class.java)
        result.keyStoreType shouldBe "test"
        result.keyStorePath shouldBe "test"
        result.keySorePassword shouldBe "test"
        result.keyAlias shouldBe "test"
        result.ports?.size shouldBe 2
        result.securePorts?.size shouldBe 2
        result.endPoints?.size shouldBe 1
        result.endPoints?.get(0)?.rules?.size shouldBe 1
    }

}) {
    companion object {
        const val xml = """
            {
  "extensionDir" : "test",
  "keyStoreType" : "test",
  "keyStorePath" : "test",
  "keySorePassword" : "test",
  "keyAlias" : "test",
  "ports" : [ 8089, 8090 ],
  "securePorts" : [ 9089, 9090 ],
  "testCaseTimeout" : 1000,
  "endPoints" : [ {
    "id" : "test",
    "type" : "test",
    "properties" : {
      "test" : "test"
    },
    "rules" : [ {
      "@class" : "org.adaptable.common.api.StandardRule",
      "expression" : "'true'",
      "response" : {
        "@class" : "org.adaptable.common.api.utils.StubResponse",
        "status" : 200,
        "body" : "Hello world"
      },
      "responses" : null
    } ],
    "messageConverter" : null,
    "unavailable" : null
  } ]
}
"""
    }
}
