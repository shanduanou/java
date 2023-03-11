package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.forPubNubObject.PNPage
import com.pubnub.publish.dependencies.models.PNChannelMetadata

data class PNChannelMetadataArrayResult(
    val status: Int,
    val data: Collection<PNChannelMetadata>,
    val totalCount: Int?,
    val next: PNPage.PNNext?,
    val prev: PNPage.PNPrev?
)

data class PNChannelMetadataResult(
    val status: Int,
    val data: PNChannelMetadata?
)
