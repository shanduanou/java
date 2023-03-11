package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.forPubNubObject.PNPage

data class PNMemberArrayResult(
    val status: Int,
    val data: Collection<PNMember>,
    val totalCount: Int?,
    val next: PNPage.PNNext?,
    val prev: PNPage.PNPrev?
)
