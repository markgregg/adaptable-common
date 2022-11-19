package org.adaptable.common.api

data class ServiceDefinition(
    val extensionDir: String?,
    val keyStoreType: String?,
    val keyStorePath: String?,
    val keySorePassword: String?,
    val keyAlias: String?,
    val ports: List<Int>?,
    val securePorts: List<Int>?,
    val testCaseTimeout: Long?,
    val endPoints: List<EndPoint>?
)