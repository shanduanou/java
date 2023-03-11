package com.pubnub.publish.dependencies.forPubNubObject

import com.pubnub.publish.dependencies.Endpoint
import com.pubnub.publish.dependencies.PNOperationType
import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.other.Envelope
import com.pubnub.publish.dependencies.other.PNWhereNowResult
import com.pubnub.publish.dependencies.other.WhereNowPayload
import retrofit2.Call
import retrofit2.Response

/**
 * @see [PubNub.whereNow]
 */
class WhereNow internal constructor(
    pubnub: PubNub,
    val uuid: String = pubnub.configuration.uuid
) : Endpoint<Envelope<WhereNowPayload>, PNWhereNowResult>(pubnub) {

    override fun doWork(queryParams: HashMap<String, String>): Call<Envelope<WhereNowPayload>> {
        return pubnub.retrofitManager.presenceService.whereNow(
            pubnub.configuration.subscribeKey,
            uuid,
            queryParams
        )
    }

    override fun createResponse(input: Response<Envelope<WhereNowPayload>>): PNWhereNowResult =
        PNWhereNowResult(channels = input.body()!!.payload!!.channels)

    override fun operationType() = PNOperationType.PNWhereNowOperation
}
