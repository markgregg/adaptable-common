package org.adaptable.common.api

interface Payload<T> {
    fun payload(): T?
}