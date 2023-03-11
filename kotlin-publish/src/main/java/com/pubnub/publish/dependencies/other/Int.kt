package com.pubnub.publish.dependencies.other

fun Int.nonPositiveToNull() = if (this < 1)
    null
else
    this

fun Int.limit(limit: Int): Int {
    return when {
        this > limit -> limit
        else -> this
    }
}
