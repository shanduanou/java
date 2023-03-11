package com.pubnub.publish.dependencies.forPubNubObject


import com.pubnub.publish.dependencies.Endpoint
import com.pubnub.publish.dependencies.PNOperationType
import com.pubnub.publish.dependencies.PubNub
import com.pubnub.publish.dependencies.other.PNTimeResult
import retrofit2.Response

/**
 * @see [PubNub.time]
 */
class Time internal constructor(pubnub: PubNub) : Endpoint<List<Long>, PNTimeResult>(pubnub) {

    override fun getAffectedChannels() = emptyList<String>()

    override fun getAffectedChannelGroups() = emptyList<String>()

    override fun doWork(queryParams: HashMap<String, String>) =
        pubnub.retrofitManager.timeService.fetchTime(queryParams)

    override fun createResponse(input: Response<List<Long>>): PNTimeResult? {
        return PNTimeResult(input.body()!![0])
    }

    override fun operationType() = PNOperationType.PNTimeOperation

    override fun isAuthRequired() = false
    override fun isSubKeyRequired() = false
}