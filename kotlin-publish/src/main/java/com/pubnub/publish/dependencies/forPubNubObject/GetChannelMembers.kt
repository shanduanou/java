package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.publish.dependencies.Endpoint
import com.pubnub.publish.dependencies.PNOperationType
import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.other.EntityArrayEnvelope
import com.pubnub.publish.dependencies.other.PNMember
import com.pubnub.publish.dependencies.other.PNMemberArrayResult
import com.pubnub.publish.dependencies.other.toPNMemberArrayResult
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.getChannelMembers]
 */
class GetChannelMembers internal constructor(
    pubnub: PubNub,
    private val channel: String,
    private val returningCollection: ReturningCollection,
    private val withUUIDDetailsCustom: ReturningUUIDDetailsCustom
) : Endpoint<EntityArrayEnvelope<PNMember>, PNMemberArrayResult>(pubnub) {

    override fun doWork(queryParams: HashMap<String, String>): Call<EntityArrayEnvelope<PNMember>> {
        val params =
            queryParams + returningCollection.createCollectionQueryParams() + withUUIDDetailsCustom.createIncludeQueryParams()
        return pubnub.retrofitManager.objectsService.getChannelMembers(
            channel = channel,
            subKey = pubnub.configuration.subscribeKey,
            options = params
        )
    }

    override fun createResponse(input: Response<EntityArrayEnvelope<PNMember>>): PNMemberArrayResult? =
        input.toPNMemberArrayResult()

    override fun operationType(): PNOperationType {
        return PNOperationType.ObjectsOperation()
    }
}
