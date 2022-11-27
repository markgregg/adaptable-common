package io.github.markgregg.common.api.annotations

@Target(AnnotationTarget.CLASS)
annotation class EndPoint(
    val value: String,
    val endPointType: EndPointType = EndPointType.Internal,
    val url: String = ""
)
