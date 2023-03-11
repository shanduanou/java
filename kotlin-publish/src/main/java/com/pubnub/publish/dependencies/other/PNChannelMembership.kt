package com.pubnub.publish.dependencies.other


import com.pubnub.publish.dependencies.models.PNChannelMetadata

data class PNChannelMembership(
    val channel: PNChannelMetadata?,
    val custom: Any?,
    val updated: String,
    val eTag: String
)
