package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.forPubNubObject.PNPage
import com.pubnub.publish.dependencies.models.PNUUIDMetadata

data class PNUUIDMetadataArrayResult(
    val status: Int,
    val data: Collection<PNUUIDMetadata>,
    val totalCount: Int?,
    val next: PNPage.PNNext?,
    val prev: PNPage.PNPrev?
)

data class PNUUIDMetadataResult(
    val status: Int,
    val data: PNUUIDMetadata?
)
