package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.forPubNubObject.PNPage

data class PNChannelMembershipArrayResult(
    val status: Int,
    val data: Collection<PNChannelMembership>,
    val totalCount: Int?,
    val next: PNPage.PNNext?,
    val prev: PNPage.PNPrev?
)
