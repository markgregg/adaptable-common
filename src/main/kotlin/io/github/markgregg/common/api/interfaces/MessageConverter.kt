package io.github.markgregg.common.api.interfaces

interface MessageConverter {
    fun convert(payload: Any?): String?
}