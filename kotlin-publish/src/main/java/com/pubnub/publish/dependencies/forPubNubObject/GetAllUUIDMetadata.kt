package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.publish.dependencies.Endpoint
import com.pubnub.publish.dependencies.PNOperationType
import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.models.PNUUIDMetadata
import com.pubnub.publish.dependencies.other.EntityArrayEnvelope
import com.pubnub.publish.dependencies.other.PNUUIDMetadataArrayResult
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.getAllUUIDMetadata]
 */
class GetAllUUIDMetadata internal constructor(
    pubnub: PubNub,
    private val returningCollection: ReturningCollection,
    private val withCustom: ReturningCustom
) : Endpoint<EntityArrayEnvelope<PNUUIDMetadata>, PNUUIDMetadataArrayResult>(pubnub) {

    override fun doWork(queryParams: HashMap<String, String>): Call<EntityArrayEnvelope<PNUUIDMetadata>> {
        val params = queryParams + returningCollection.createCollectionQueryParams() + withCustom.createIncludeQueryParams()

        return pubnub.retrofitManager.objectsService.getAllUUIDMetadata(
            subKey = pubnub.configuration.subscribeKey,
            options = params
        )
    }

    override fun createResponse(input: Response<EntityArrayEnvelope<PNUUIDMetadata>>): PNUUIDMetadataArrayResult? {
        return input.body()?.let { arrayEnvelope ->
            PNUUIDMetadataArrayResult(
                status = arrayEnvelope.status,
                data = arrayEnvelope.data,
                prev = arrayEnvelope.prev?.let { PNPage.PNPrev(it) },
                next = arrayEnvelope.next?.let { PNPage.PNNext(it) },
                totalCount = arrayEnvelope.totalCount
            )
        }
    }

    override fun operationType(): PNOperationType {
        return PNOperationType.PNGetAllUUIDMetadataOperation
    }
}