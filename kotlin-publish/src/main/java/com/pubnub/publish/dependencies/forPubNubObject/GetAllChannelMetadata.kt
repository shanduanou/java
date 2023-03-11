package com.pubnub.publish.dependencies.forPubNubObject


import com.pubnub.publish.dependencies.Endpoint
import com.pubnub.publish.dependencies.PNOperationType
import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.models.PNChannelMetadata
import com.pubnub.publish.dependencies.other.EntityArrayEnvelope
import com.pubnub.publish.dependencies.other.PNChannelMetadataArrayResult
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.getAllChannelMetadata]
 */
class GetAllChannelMetadata internal constructor(
    pubnub: PubNub,
    private val returningCollection: ReturningCollection,
    private val withCustom: ReturningCustom
) : Endpoint<EntityArrayEnvelope<PNChannelMetadata>, PNChannelMetadataArrayResult>(pubnub) {

    override fun doWork(queryParams: HashMap<String, String>): Call<EntityArrayEnvelope<PNChannelMetadata>> {
        val params = queryParams + returningCollection.createCollectionQueryParams() + withCustom.createIncludeQueryParams()

        return pubnub.retrofitManager.objectsService.getAllChannelMetadata(
            subKey = pubnub.configuration.subscribeKey,
            options = params
        )
    }

    override fun createResponse(input: Response<EntityArrayEnvelope<PNChannelMetadata>>): PNChannelMetadataArrayResult? {
        return input.body()?.let { arrayEnvelope ->
            PNChannelMetadataArrayResult(
                status = arrayEnvelope.status,
                data = arrayEnvelope.data,
                prev = arrayEnvelope.prev?.let { PNPage.PNPrev(it) },
                next = arrayEnvelope.next?.let { PNPage.PNNext(it) },
                totalCount = arrayEnvelope.totalCount
            )
        }
    }

    override fun operationType(): PNOperationType {
        return PNOperationType.PNGetAllChannelsMetadataOperation
    }
}
