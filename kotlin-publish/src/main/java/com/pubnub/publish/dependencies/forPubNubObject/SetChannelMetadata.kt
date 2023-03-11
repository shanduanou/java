package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.api.Endpoint
import com.pubnub.api.PubNub
import com.pubnub.api.enums.PNOperationType
import com.pubnub.api.models.consumer.objects.channel.PNChannelMetadata
import com.pubnub.api.models.consumer.objects.channel.PNChannelMetadataResult
import com.pubnub.api.models.server.objects_api.ChannelMetadataInput
import com.pubnub.api.models.server.objects_api.EntityEnvelope
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.setChannelMetadata]
 */
class SetChannelMetadata internal constructor(
    pubnub: PubNub,
    private val name: String?,
    private val description: String?,
    private val custom: Any?,
    private val channel: String,
    private val withCustom: ReturningCustom
) : Endpoint<EntityEnvelope<PNChannelMetadata>, PNChannelMetadataResult>(pubnub) {

    override fun doWork(queryParams: HashMap<String, String>): Call<EntityEnvelope<PNChannelMetadata>> {
        val params = queryParams + withCustom.createIncludeQueryParams()
        return pubnub.retrofitManager.objectsService.setChannelMetadata(
            subKey = pubnub.configuration.subscribeKey,
            body = ChannelMetadataInput(
                name = name,
                custom = custom,
                description = description
            ),
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
        return PNOperationType.PNSetChannelMetadataOperation
    }
}
