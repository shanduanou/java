package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.publish.dependencies.Endpoint
import com.pubnub.publish.dependencies.PNOperationType
import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.other.EntityArrayEnvelope
import com.pubnub.publish.dependencies.other.PNChannelMembership
import com.pubnub.publish.dependencies.other.PNChannelMembershipArrayResult
import com.pubnub.publish.dependencies.other.toPNChannelMembershipArrayResult
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.getMemberships]
 */
class GetMemberships internal constructor(
    pubnub: PubNub,
    private val uuid: String,
    private val returningCollection: ReturningCollection,
    private val withChannelDetailsCustom: ReturningChannelDetailsCustom
) : Endpoint<EntityArrayEnvelope<PNChannelMembership>, PNChannelMembershipArrayResult>(pubnub) {

    override fun doWork(queryParams: HashMap<String, String>): Call<EntityArrayEnvelope<PNChannelMembership>> {
        val params =
            queryParams + returningCollection.createCollectionQueryParams() + withChannelDetailsCustom.createIncludeQueryParams()

        return pubnub.retrofitManager.objectsService.getMemberships(
            uuid = uuid,
            subKey = pubnub.configuration.subscribeKey,
            options = params
        )
    }

    override fun createResponse(input: Response<EntityArrayEnvelope<PNChannelMembership>>): PNChannelMembershipArrayResult? =
        input.toPNChannelMembershipArrayResult()

    override fun operationType(): PNOperationType {
        return PNOperationType.PNGetMembershipsOperation
    }
}