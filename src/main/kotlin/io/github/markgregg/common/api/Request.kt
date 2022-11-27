package io.github.markgregg.common.api

import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.markgregg.expression.Context

@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
interface Request : Context