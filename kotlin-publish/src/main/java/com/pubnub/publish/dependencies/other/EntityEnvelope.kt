package com.pubnub.publish.dependencies.other

data class EntityEnvelope<T>(
    val status: Int,
    val data: T? = null
)
