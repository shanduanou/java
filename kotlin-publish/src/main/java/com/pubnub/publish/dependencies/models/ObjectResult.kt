package com.pubnub.publish.dependencies.models

interface ObjectResult<T> {
    val event: String
    val data: T
}
