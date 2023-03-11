package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.publish.dependencies.Endpoint
import com.pubnub.publish.dependencies.PNOperationType
import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.models.PNChannelMetadata
import com.pubnub.publish.dependencies.other.EntityEnvelope
import com.pubnub.publish.dependencies.other.PNChannelMetadataResult
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.getChannelMetadata]
 */
class GetChannelMetadata internal constructor(
    pubnub: PubNub,
    private val channel: String,
    private val withCustom: ReturningCustom
) : Endpoint<EntityEnvelope<PNChannelMetadata>, PNChannelMetadataResult>(pubnub) {

    override fun doWork(queryParams: HashMap<String, String>): Call<EntityEnvelope<PNChannelMetadata>> {
        val params = queryParams + withCustom.createIncludeQueryParams()
        return pubnub.retrofitManager.objectsService.getChannelMetadata(
            subKey = pubnub.configuration.subscribeKey,
            channel = channel,
            options = params
        )
    }

    override fun createResponse(input: Response<EntityEnvelope<PNChannelMetadata>>): PNChannelMetadataResult? {
        return input.body()?.let {
            PNChannelMetadataResult(
                status = it.status,
                data = it.data
            )
        }
    }

    override fun operationType(): PNOperationType {
        return PNOperationType.PNGetChannelMetadataOperation
    }
}