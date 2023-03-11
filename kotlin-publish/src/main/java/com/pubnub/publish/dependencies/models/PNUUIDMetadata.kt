package com.pubnub.publish.dependencies.models

data class PNUUIDMetadata(
    val id: String,
    val name: String?,
    val externalId: String?,
    val profileUrl: String?,
    val email: String?,
    val custom: Any?,
    val updated: String?,
    val eTag: String?
)