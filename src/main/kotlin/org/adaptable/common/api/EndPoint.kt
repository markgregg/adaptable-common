package org.adaptable.common.api

data class EndPoint(
    val id: String,
    val type: String?,
    val properties: Map<String,String>? = null,
    val rules: List<Rule>? = null,
    val messageConverter: String? = null,
    val unavailable: Boolean? = null,
    val sendsResponse: Boolean? = null
) {
    constructor(id: String, rules: List<Rule>, unavailable: Boolean, sendsResponse: Boolean) : this(id, null, null, rules, null, unavailable, sendsResponse)
}