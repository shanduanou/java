package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.publish.dependencies.Endpoint
import com.pubnub.publish.dependencies.PNOperationType
import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.models.PNUUIDMetadata
import com.pubnub.publish.dependencies.other.EntityEnvelope
import com.pubnub.publish.dependencies.other.PNUUIDMetadataResult
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.getUUIDMetadata]
 */
class GetUUIDMetadata internal constructor(
    pubnub: PubNub,
    val uuid: String,
    private val withCustom: ReturningCustom
) : Endpoint<EntityEnvelope<PNUUIDMetadata>, PNUUIDMetadataResult>(pubnub) {

    override fun doWork(queryParams: HashMap<String, String>): Call<EntityEnvelope<PNUUIDMetadata>> {
        val params = queryParams + withCustom.createIncludeQueryParams()
        return pubnub.retrofitManager.objectsService.getUUIDMetadata(
            subKey = pubnub.configuration.subscribeKey,
            uuid = uuid,
            options = params
        )
    }

    override fun createResponse(input: Response<EntityEnvelope<PNUUIDMetadata>>): PNUUIDMetadataResult? {
        return input.body()?.let {
            PNUUIDMetadataResult(
                status = it.status,
                data = it.data
            )
        }
    }

    override fun operationType(): PNOperationType {
        return PNOperationType.PNGetUUIDMetadataOperation
    }
}