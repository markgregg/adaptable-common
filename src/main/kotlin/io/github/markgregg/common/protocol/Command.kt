package org.adaptable.common.protocol

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
interface Command