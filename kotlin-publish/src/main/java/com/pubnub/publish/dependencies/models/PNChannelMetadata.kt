package com.pubnub.publish.dependencies.models

data class PNChannelMetadata(
    val id: String,
    val name: String?,
    val description: String?,
    val custom: Any?,
    val updated: String?,
    val eTag: String?
)