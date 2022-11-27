package io.github.markgregg.common.api.utils

import io.github.markgregg.common.api.Response

data class StubResponse (
    val status: Int,
    val body: String
) : io.github.markgregg.common.api.Response