package io.github.markgregg.common.api

interface Payload<T> {
    fun payload(): T?
}