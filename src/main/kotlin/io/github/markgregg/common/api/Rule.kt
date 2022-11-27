package io.github.markgregg.common.api

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
interface Rule {
    fun evaluate(request: Request) : Response?
}