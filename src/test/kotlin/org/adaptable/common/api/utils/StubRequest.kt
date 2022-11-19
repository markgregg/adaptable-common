package org.adaptable.common.api.utils

import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.adaptable.common.api.Request

class StubRequest(
    val parameters: Map<String,String>?,
    val body: String
) : Request {
    private var json: ObjectNode? = null

    override fun getItem(name: String): Any {
        return when(name) {
            "text" -> body
            "body" -> json()
            "parameter" -> try {
                jacksonObjectMapper().valueToTree(parameters)
            } catch (e: Exception) {
                jacksonObjectMapper().createObjectNode()
            }
            else -> throw Exception("$name is not part of this request")
        }
    }

    private fun json(): ObjectNode {
        if( json == null ) {
            json = try {
                jacksonObjectMapper().readTree(body) as? ObjectNode
                    ?: jacksonObjectMapper().createObjectNode()
            }  catch (e: Exception) {
                jacksonObjectMapper().createObjectNode()
            }
        }
        return json!!
    }

}