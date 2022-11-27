package io.github.markgregg.common.protocol

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
abstract class Response(
    val success: Boolean,
    val message: String?
)