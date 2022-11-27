package org.adaptable.common.api.interfaces

interface EndPointConfiguration {
    val testCaseTimeout: Long?
    val endPoints: Map<String, EndPoint>

    fun <T : EndPoint> getEndPoint(id: String, type: Class<T>): T?
    fun <T : EndPoint> getEndPoints(type: Class<T>): List<T>
}