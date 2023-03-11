package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.models.PNUUIDMetadata

data class PNMember(
    val uuid: PNUUIDMetadata?,
    val custom: Any? = null,
    val updated: String,
    val eTag: String
)
