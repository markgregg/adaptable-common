package org.adaptable.common.api

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
interface Response