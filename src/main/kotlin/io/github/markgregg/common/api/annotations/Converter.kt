package io.github.markgregg.common.api.annotations

@Target(AnnotationTarget.CLASS)
annotation class Converter(
    val value: String
)