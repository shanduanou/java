package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.forPubNubObject.PNPage
import retrofit2.Response

internal fun Response<EntityArrayEnvelope<PNMember>>.toPNMemberArrayResult(): PNMemberArrayResult? =
    body()?.let { arrayEnvelope ->
        PNMemberArrayResult(
            status = arrayEnvelope.status,
            data = arrayEnvelope.data,
            totalCount = arrayEnvelope.totalCount,
            next = arrayEnvelope.next?.let { PNPage.PNNext(it) },
            prev = arrayEnvelope.prev?.let { PNPage.PNPrev(it) }
        )
    }

internal fun Response<EntityArrayEnvelope<PNChannelMembership>>.toPNChannelMembershipArrayResult(): PNChannelMembershipArrayResult? =
    body()?.let { arrayEnvelope ->
        PNChannelMembershipArrayResult(
            status = arrayEnvelope.status,
            data = arrayEnvelope.data,
            totalCount = arrayEnvelope.totalCount,
            next = arrayEnvelope.next?.let { PNPage.PNNext(it) },
            prev = arrayEnvelope.prev?.let { PNPage.PNPrev(it) }
        )
    }