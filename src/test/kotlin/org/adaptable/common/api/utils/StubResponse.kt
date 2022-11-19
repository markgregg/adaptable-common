package org.adaptable.common.api.utils

import org.adaptable.common.api.Response

data class StubResponse (
    val status: Int,
    val body: String
) : Response