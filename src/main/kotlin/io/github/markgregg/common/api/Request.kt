package org.adaptable.common.api

import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.adaptable.expression.Context

@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
interface Request : Context