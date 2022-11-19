package org.adaptable.common.api.interfaces

interface MessageConverter {
    fun convert(payload: Any?): String?
}